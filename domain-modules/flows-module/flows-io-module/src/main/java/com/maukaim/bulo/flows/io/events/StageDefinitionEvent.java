package com.maukaim.bulo.flows.io.events;

import com.maukaim.bulo.io.definitions.shared.DefinitionEventType;
import com.maukaim.bulo.commons.io.GlobalDefinitionEvent;
import com.maukaim.bulo.flows.io.definition.stageDefinitionDto;

import java.time.Instant;

public class StageDefinitionEvent implements GlobalDefinitionEvent {
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

    @Override
    public DefinitionEventType getEventType() {
        return eventType;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }
}
