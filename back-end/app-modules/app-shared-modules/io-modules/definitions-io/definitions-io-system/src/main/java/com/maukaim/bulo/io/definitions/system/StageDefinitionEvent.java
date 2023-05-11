package com.maukaim.bulo.io.definitions.system;

import com.maukaim.bulo.io.definitions.system.dtos.DefinitionEventType;
import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;

import java.time.Instant;

public class StageDefinitionEvent {
    private final StageDefinitionDto stageDefinition;
    private final DefinitionEventType eventType;
    private final Instant instant;

    public StageDefinitionEvent(StageDefinitionDto stageDefinition, DefinitionEventType eventType, Instant instant) {
        this.stageDefinition = stageDefinition;
        this.eventType = eventType;
        this.instant = instant;
    }

    public StageDefinitionDto getStageDefinition() {
        return stageDefinition;
    }

    public DefinitionEventType getEventType() {
        return eventType;
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "StageDefinitionEvent{" +
                "stageDefinition=" + stageDefinition +
                ", eventType=" + eventType +
                ", instant=" + instant +
                '}';
    }
}
