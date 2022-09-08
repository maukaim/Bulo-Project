package com.maukaim.bulo.definitions.io.events;

import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.commons.io.GlobalDefinitionEvent;
import com.maukaim.bulo.definitions.io.models.TechnicalStageDefinitionDto;

import java.time.Instant;

public class TechnicalStageDefinitionEvent implements GlobalDefinitionEvent {
    private TechnicalStageDefinitionDto technicalStageDefinition;
    private DefinitionEventType eventType;
    private Instant instant;

    public TechnicalStageDefinitionEvent(TechnicalStageDefinitionDto technicalStageDefinition, DefinitionEventType eventType, Instant instant) {
        this.technicalStageDefinition = technicalStageDefinition;
        this.eventType = eventType;
        this.instant = instant;
    }

    public TechnicalStageDefinitionDto getTechnicalStageDefinition() {
        return technicalStageDefinition;
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
