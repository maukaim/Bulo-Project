package com.maukaim.bulo.definitions.registry.io;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

import java.time.Instant;

public class TechnicalStageDefinitionDeclarationEvent implements ExternalEvent {
    private String stageExecutorId;
    private TechnicalStageDefinition technicalStageDefinition;
    private Instant instant;

    public TechnicalStageDefinitionDeclarationEvent(String stageExecutorId, TechnicalStageDefinition technicalStageDefinition, Instant instant) {
        this.stageExecutorId = stageExecutorId;
        this.technicalStageDefinition = technicalStageDefinition;
        this.instant = instant;
    }

    public String getStageExecutorId() {
        return stageExecutorId;
    }

    public TechnicalStageDefinition getTechnicalStageDefinition() {
        return technicalStageDefinition;
    }

    @Override
    public Instant getInstant() {
        return null;
    }

    @Override
    public String toString() {
        return "DeclareStageModelEvent{" +
                "stageExecutorId='" + stageExecutorId + '\'' +
                ", stageModel=" + technicalStageDefinition +
                ", instant=" + instant +
                '}';
    }
}
