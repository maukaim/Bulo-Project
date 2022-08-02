package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.registry.io.TechnicalStageDefinitionPublisher;
import com.maukaim.bulo.definitions.registry.io.model.TSDEventType;
import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

import java.util.List;

public class TechnicalStageDefinitionServiceImpl implements TechnicalStageDefinitionService {
    private final List<TechnicalStageDefinitionValidator> validators;
    private final TechnicalStageDefinitionRepository definitionRepository;
    private final TechnicalStageDefinitionPublisher definitionPublisher;

    public TechnicalStageDefinitionServiceImpl(TechnicalStageDefinitionRepository definitionRepository,
                                               List<TechnicalStageDefinitionValidator> validators,
                                               TechnicalStageDefinitionPublisher definitionPublisher) {
        this.definitionRepository = definitionRepository;
        this.validators = validators;
        this.definitionPublisher = definitionPublisher;
    }

    @Override
    public void add(String stageExecutorId, TechnicalStageDefinition definition) {
        TechnicalStageDefinition existingDefinition = definitionRepository.get(definition.getTechnicalStageDefinitionId());
        if (existingDefinition != null && existingDefinition.equals(definition)) {
            this.definitionRepository.addExecutor(stageExecutorId, definition.getTechnicalStageDefinitionId());
        } else if (this.validators.stream().allMatch(validator -> validator.validate(definition))) {
            this.definitionRepository.putDefinition(definition);
            this.definitionRepository.addExecutor(stageExecutorId, definition.getTechnicalStageDefinitionId());
        } else {
            throw new RuntimeException("Definition rejected: " + definition.getTechnicalStageDefinitionId());
        }
        this.definitionPublisher.publish(TSDEventType.UPDATE, definition);
    }

    @Override
    public TechnicalStageDefinition get(String definitionId) {
        return this.definitionRepository.get(definitionId);
    }

    @Override
    public List<TechnicalStageDefinition> getAll() {
        return this.definitionRepository.getAll();
    }
}
