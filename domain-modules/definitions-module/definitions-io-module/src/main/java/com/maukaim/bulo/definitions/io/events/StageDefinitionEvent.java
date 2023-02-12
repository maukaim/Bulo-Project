package com.maukaim.bulo.definitions.io.events;

import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.commons.io.GlobalDefinitionEvent;
import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;

import java.time.Instant;

public class StageDefinitionEvent implements GlobalDefinitionEvent {
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

    @Override
    public DefinitionEventType getEventType() {
        return eventType;
    }

    @Override
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
