package com.maukaim.bulo.io;

import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.commons.io.GlobalDefinitionEvent;
import com.maukaim.bulo.io.definitions.TechnicalStageDefinitionData;

import java.time.Instant;

public class TechnicalStageDefinitionEvent implements GlobalDefinitionEvent {
    private TechnicalStageDefinitionData technicalStageDefinition;
    private DefinitionEventType eventType;
    private Instant instant;

    public TechnicalStageDefinitionEvent(TechnicalStageDefinitionData technicalStageDefinition, DefinitionEventType eventType, Instant instant) {
        this.technicalStageDefinition = technicalStageDefinition;
        this.eventType = eventType;
        this.instant = instant;
    }

    public TechnicalStageDefinitionData getTechnicalStageDefinition() {
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
