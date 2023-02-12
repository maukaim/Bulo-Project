package com.maukaim.bulo.definitions.io.events;

import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.commons.io.GlobalDefinitionEvent;
import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;

import java.time.Instant;

public class ExecutorUpdateEvent implements GlobalDefinitionEvent {
    private String executorId;
    private String definitionId;
    private DefinitionEventType eventType;
    private Instant instant;

    public ExecutorUpdateEvent(String executorId, String definitionId, DefinitionEventType eventType, Instant instant) {
        this.executorId = executorId;
        this.definitionId = definitionId;
        this.eventType = eventType;
        this.instant = instant;
    }

    public String getExecutorId() {
        return executorId;
    }

    public String getDefinitionId() {
        return definitionId;
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
        return "ExecutorUpdateEvent{" +
                "executorId='" + executorId + '\'' +
                ", definitionId='" + definitionId + '\'' +
                ", eventType=" + eventType +
                ", instant=" + instant +
                '}';
    }
}
