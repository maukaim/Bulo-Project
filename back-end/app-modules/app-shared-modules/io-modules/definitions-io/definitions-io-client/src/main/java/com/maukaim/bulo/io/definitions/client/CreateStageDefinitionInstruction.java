package com.maukaim.bulo.io.definitions.client;

import com.maukaim.bulo.io.shared.ExternalEvent;
import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;

import java.time.Instant;

public class CreateStageDefinitionInstruction implements ExternalEvent {
    private final String stageExecutorId;
    private final StageDefinitionDto stageDefinition;
    private final Instant instant;

    public CreateStageDefinitionInstruction(String stageExecutorId, StageDefinitionDto stageDefinition, Instant instant) {
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
        return instant;
    }

    @Override
    public String toString() {
        return "StageDefinitionCreateInstruction{" +
                "stageExecutorId='" + stageExecutorId + '\'' +
                ", stageDefinition=" + stageDefinition +
                ", instant=" + instant +
                '}';
    }
}
