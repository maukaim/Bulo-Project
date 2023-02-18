package com.maukaim.bulo.io.stages.events;

import com.maukaim.bulo.io.stages.models.definitions.DefinitionEventType;
import com.maukaim.bulo.io.stages.models.definitions.StageDefinitionDto;

import java.time.Instant;

public class TechnicalStageDefinitionEvent {
    private StageDefinitionDto technicalStageDefinition;
    private DefinitionEventType eventType;
    private Instant instant;

    public TechnicalStageDefinitionEvent(StageDefinitionDto technicalStageDefinition, DefinitionEventType eventType, Instant instant) {
        this.technicalStageDefinition = technicalStageDefinition;
        this.eventType = eventType;
        this.instant = instant;
    }

    public StageDefinitionDto getTechnicalStageDefinition() {
        return technicalStageDefinition;
    }

    public DefinitionEventType getEventType() {
        return eventType;
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "TechnicalStageDefinitionEvent{" +
                "technicalStageDefinition=" + technicalStageDefinition +
                ", eventType=" + eventType +
                ", instant=" + instant +
                '}';
    }
}
