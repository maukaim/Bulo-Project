package com.maukaim.bulo.flows.io.events;

import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.commons.io.GlobalDefinitionEvent;
import com.maukaim.bulo.flows.io.definition.TechnicalStageDefinitionDto;

import java.time.Instant;

public class TechnicalStageDefinitionEvent implements GlobalDefinitionEvent {
    private TechnicalStageDefinitionDto technicalStageDefinitionDto;
    private DefinitionEventType eventType;
    private Instant instant;

    public TechnicalStageDefinitionEvent(TechnicalStageDefinitionDto technicalStageDefinitionDto, DefinitionEventType eventType, Instant instant) {
        this.technicalStageDefinitionDto = technicalStageDefinitionDto;
        this.eventType = eventType;
        this.instant = instant;
    }

    public TechnicalStageDefinitionDto getTechnicalStageDefinition() {
        return technicalStageDefinitionDto;
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
