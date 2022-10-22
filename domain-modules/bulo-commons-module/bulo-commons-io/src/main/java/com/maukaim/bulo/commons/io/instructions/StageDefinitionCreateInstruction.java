package com.maukaim.bulo.commons.io.instructions;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;

import java.time.Instant;

public class StageDefinitionCreateInstruction implements ExternalEvent {
    private String stageExecutorId;
    private StageDefinitionDto stageDefinition;
    private Instant instant;

    public StageDefinitionCreateInstruction(String stageExecutorId, StageDefinitionDto stageDefinition, Instant instant) {
        this.stageExecutorId = stageExecutorId;
        this.stageDefinition = stageDefinition;
        this.instant = instant;
    }

    public String getStageExecutorId() {
        return stageExecutorId;
    }

    public StageDefinitionDto getStageDefinition() {
        return stageDefinition;
    }

    @Override
    public Instant getInstant() {
        return null;
    }

    @Override
    public String toString() {
        return "TechnicalStageDefinitionDeclarationEvent{" +
                "stageExecutorId='" + stageExecutorId + '\'' +
                ", stageDefinition=" + stageDefinition +
                ", instant=" + instant +
                '}';
    }
}
