package com.maukaim.bulo.flows.io.events;

import com.maukaim.bulo.flows.io.definition.stageDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.DefinitionEventType;

import java.time.Instant;

public class StageDefinitionEvent {
    private stageDefinitionDto stageDefinitionDto;
    private DefinitionEventType eventType;
    private Instant instant;

    public StageDefinitionEvent(stageDefinitionDto stageDefinitionDto, DefinitionEventType eventType, Instant instant) {
        this.stageDefinitionDto = stageDefinitionDto;
        this.eventType = eventType;
        this.instant = instant;
    }

    public stageDefinitionDto getStageDefinition() {
        return stageDefinitionDto;
    }

    public DefinitionEventType getEventType() {
        return eventType;
    }

    public Instant getInstant() {
        return instant;
    }
}
