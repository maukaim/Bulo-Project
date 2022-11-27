package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;

import java.util.List;
import java.util.UUID;

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
    public void register(FunctionalStageDefinition definition) {
        StageDefinition existingDefinition = definitionStore.getById(definition.getDefinitionId());
        if (existingDefinition == null) {
            if (this.functionalValidators.stream().allMatch(validator -> validator.validate(definition))) {
                this.definitionStore.addDefinition(attachUUID(definition));
            } else {
                throw new RuntimeException("Definition rejected: " + definition.getDefinitionId());
            }
        } else {
            throw new RuntimeException("Definition already exist : " + existingDefinition);
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
    public void register(String stageExecutorId, TechnicalStageDefinition definition) {
        StageDefinition existingDefinition = definitionStore.getById(definition.getDefinitionId());
        if (existingDefinition != null && existingDefinition.equals(definition)) {
            this.definitionStore.addExecutor(stageExecutorId, definition.getDefinitionId());
        } else if (this.technicalValidators.stream().allMatch(validator -> validator.validate(definition))) {
            this.definitionStore.addDefinition(definition);
            this.definitionStore.addExecutor(stageExecutorId, definition.getDefinitionId());
        } else {
            throw new RuntimeException("Definition rejected: " + definition.getDefinitionId());
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
