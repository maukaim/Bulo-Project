package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.data.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.StageDefinitionStore;

import java.util.List;

public class TechnicalStageDefinitionServiceImpl implements TechnicalStageDefinitionService {
    private final List<TechnicalStageDefinitionValidator> validators;
    private final StageDefinitionStore definitionStore;

    public TechnicalStageDefinitionServiceImpl(StageDefinitionStore definitionStore,
                                               List<TechnicalStageDefinitionValidator> validators) {
        this.definitionStore = definitionStore;
        this.validators = validators;
    }

    @Override
    public void register(FunctionalStageDefinition functionalStageDefinition) {
        TechnicalStageDefinition existingDefinition = definitionStore.getById(definition.getId());
    }

    @Override
    public void register(String stageExecutorId, TechnicalStageDefinition definition) {
        TechnicalStageDefinition existingDefinition = definitionStore.getById(definition.getId());
        if (existingDefinition != null && existingDefinition.equals(definition)) {
            this.definitionStore.addExecutor(stageExecutorId, definition.getId());
        } else if (this.validators.stream().allMatch(validator -> validator.validate(definition))) {
            this.definitionStore.addDefinition(definition);
            this.definitionStore.addExecutor(stageExecutorId, definition.getId());
        } else {
            throw new RuntimeException("Definition rejected: " + definition.getId());
        }
    }

    @Override
    public void unregister(String stageExecutorId, String definitionId) {
        this.definitionStore.removeExecutor(stageExecutorId,definitionId);
    }

    @Override
    public TechnicalStageDefinition get(String definitionId) {
        return this.definitionStore.getById(definitionId);
    }

    @Override
    public List<TechnicalStageDefinition> getAll() {
        return this.definitionStore.getAll();
    }
}
