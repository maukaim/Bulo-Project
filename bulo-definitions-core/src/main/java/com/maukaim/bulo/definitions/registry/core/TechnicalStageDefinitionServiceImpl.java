package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.TechnicalStageDefinitionStore;

import java.util.List;

public class TechnicalStageDefinitionServiceImpl implements TechnicalStageDefinitionService {
    private final List<TechnicalStageDefinitionValidator> validators;
    private final TechnicalStageDefinitionStore definitionStore;

    public TechnicalStageDefinitionServiceImpl(TechnicalStageDefinitionStore definitionStore,
                                               List<TechnicalStageDefinitionValidator> validators) {
        this.definitionStore = definitionStore;
        this.validators = validators;
    }

    @Override
    public void register(String stageExecutorId, TechnicalStageDefinition definition) {
        TechnicalStageDefinition existingDefinition = definitionStore.getById(definition.getTechnicalStageDefinitionId());
        if (existingDefinition != null && existingDefinition.equals(definition)) {
            this.definitionStore.addExecutor(stageExecutorId, definition.getTechnicalStageDefinitionId());
        } else if (this.validators.stream().allMatch(validator -> validator.validate(definition))) {
            this.definitionStore.addDefinition(definition);
            this.definitionStore.addExecutor(stageExecutorId, definition.getTechnicalStageDefinitionId());
        } else {
            throw new RuntimeException("Definition rejected: " + definition.getTechnicalStageDefinitionId());
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
