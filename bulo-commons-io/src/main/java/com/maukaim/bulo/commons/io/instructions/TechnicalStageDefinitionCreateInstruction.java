package com.maukaim.bulo.definitions.io.events;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.definitions.io.models.TechnicalStageDefinitionDto;

import java.time.Instant;

public class TechnicalStageDefinitionDeclarationEvent implements ExternalEvent {
    private String stageExecutorId;
    private TechnicalStageDefinitionDto technicalStageDefinition;
    private Instant instant;

    public TechnicalStageDefinitionDeclarationEvent(String stageExecutorId, TechnicalStageDefinitionDto technicalStageDefinition, Instant instant) {
        this.stageExecutorId = stageExecutorId;
        this.technicalStageDefinition = technicalStageDefinition;
        this.instant = instant;
    }

    public String getStageExecutorId() {
        return stageExecutorId;
    }

    public TechnicalStageDefinitionDto getTechnicalStageDefinition() {
        return technicalStageDefinition;
    }

    @Override
    public Instant getInstant() {
        return null;
    }

    @Override
    public String toString() {
        return "TechnicalStageDefinitionDeclarationEvent{" +
                "stageExecutorId='" + stageExecutorId + '\'' +
                ", technicalStageDefinition=" + technicalStageDefinition +
                ", instant=" + instant +
                '}';
    }
}
