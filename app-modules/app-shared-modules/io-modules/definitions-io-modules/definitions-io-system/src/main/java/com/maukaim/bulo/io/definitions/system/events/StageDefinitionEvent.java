package com.maukaim.bulo.io.definitions.system.events;

import com.maukaim.bulo.io.definitions.system.events.model.DefinitionEventType;
import com.maukaim.bulo.io.definitions.client.models.StageDefinitionDto;

import java.time.Instant;

public class StageDefinitionEvent {
    private StageDefinitionDto stageDefinition;
    private DefinitionEventType eventType;
    private Instant instant;

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
