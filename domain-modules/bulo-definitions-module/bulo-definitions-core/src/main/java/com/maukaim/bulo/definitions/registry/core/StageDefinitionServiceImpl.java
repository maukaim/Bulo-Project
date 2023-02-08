package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.report.StageDefinitionCreateReport;

import java.util.List;
import java.util.UUID;

import static com.maukaim.bulo.definitions.registry.core.report.StageDefinitionCreateReport.DEFAULT_SUCCESS_REPORT;

public class StageDefinitionServiceImpl implements StageDefinitionService {
    private final List<TechnicalStageDefinitionValidator> technicalValidators;
    private final List<FunctionalStageDefinitionValidator> functionalValidators;
    private final StageDefinitionStore definitionStore;

    public StageDefinitionServiceImpl(StageDefinitionStore definitionStore,
                                      List<TechnicalStageDefinitionValidator> technicalValidators, List<FunctionalStageDefinitionValidator> functionalValidators) {
        this.definitionStore = definitionStore;
        this.technicalValidators = technicalValidators;
        this.functionalValidators = functionalValidators;
    }

    @Override
    public StageDefinitionCreateReport register(FunctionalStageDefinition definition) {
        StageDefinition existingDefinition = definitionStore.getById(definition.getDefinitionId());
        if (existingDefinition == null) {
            if (this.functionalValidators.stream().allMatch(validator -> validator.isValid(definition))) {
                StageDefinition stageDefinition = this.definitionStore.addDefinition(attachUUID(definition));
                return StageDefinitionCreateReport.successReport(stageDefinition.getDefinitionId(), DEFAULT_SUCCESS_REPORT);
            } else {
                return StageDefinitionCreateReport.failReport(null, "Definition rejected, did not pass ALL validators");
            }
        } else {
            return StageDefinitionCreateReport.failReport(null, "Definition already exist.");
        }
    }

    private StageDefinition attachUUID(FunctionalStageDefinition definition) {
        return new FunctionalStageDefinition(
                UUID.randomUUID().toString(),
                definition.getInputsByName(),
                definition.getOutputsByName(),
                definition.getParameters(),
                definition.getFunctionalSubStages(),
                definition.getOutputProviders());
    }

    @Override
    public StageDefinitionCreateReport register(String stageExecutorId, TechnicalStageDefinition definition) {
        StageDefinition existingDefinition = definitionStore.getById(definition.getDefinitionId());
        if (existingDefinition != null && existingDefinition.equals(definition)) {
            this.definitionStore.addExecutor(stageExecutorId, definition.getDefinitionId());
            return StageDefinitionCreateReport.successReport(existingDefinition.getDefinitionId(), "Already exist. just registered executorId.");
        } else if (this.technicalValidators.stream().allMatch(validator -> validator.validate(definition))) {
            StageDefinition stageDefinition = this.definitionStore.addDefinition(definition);
            this.definitionStore.addExecutor(stageExecutorId, definition.getDefinitionId());
            return StageDefinitionCreateReport.successReport(stageDefinition.getDefinitionId(), DEFAULT_SUCCESS_REPORT);
        } else {
            return StageDefinitionCreateReport.failReport(definition.getDefinitionId(), "Definition rejected, did not pass ALL validators");
        }
    }

    @Override
    public void unregister(String stageExecutorId, String definitionId) {
        this.definitionStore.removeExecutor(stageExecutorId, definitionId);
    }

    @Override
    public List<StageDefinition> getAll() {
        return this.definitionStore.getAll();
    }
}
