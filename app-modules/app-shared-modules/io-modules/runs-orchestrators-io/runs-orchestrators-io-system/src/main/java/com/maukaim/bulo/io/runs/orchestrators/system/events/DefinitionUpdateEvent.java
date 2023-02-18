package com.maukaim.bulo.io.runs.orchestrators.system.events;

import com.maukaim.bulo.io.runs.orchestrators.system.models.definition.DefinitionEventType;
import com.maukaim.bulo.io.runs.orchestrators.system.models.definition.StageDefinitionDto;

import java.time.Instant;

public class DefinitionUpdateEvent {
    private StageDefinitionDto stageDefinition;
    private DefinitionEventType eventType;
    private Instant instant;

    public DefinitionUpdateEvent(StageDefinitionDto stageDefinition, DefinitionEventType eventType, Instant instant) {
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
        return "DefinitionUpdateEvent{" +
                "stageDefinition=" + stageDefinition +
                ", eventType=" + eventType +
                ", instant=" + instant +
                '}';
    }
}
