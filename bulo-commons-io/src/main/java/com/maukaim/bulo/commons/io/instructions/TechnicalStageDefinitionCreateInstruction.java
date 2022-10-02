package com.maukaim.bulo.commons.io.instructions;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.commons.io.instructions.models.TechnicalStageDefinitionDto;

import java.time.Instant;

public class TechnicalStageDefinitionCreateInstruction implements ExternalEvent {
    private String stageExecutorId;
    private TechnicalStageDefinitionDto technicalStageDefinition;
    private Instant instant;

    public TechnicalStageDefinitionCreateInstruction(String stageExecutorId, TechnicalStageDefinitionDto technicalStageDefinition, Instant instant) {
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
