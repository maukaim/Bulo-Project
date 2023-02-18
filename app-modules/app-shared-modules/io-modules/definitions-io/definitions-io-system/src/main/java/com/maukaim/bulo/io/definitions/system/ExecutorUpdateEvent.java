package com.maukaim.bulo.io.definitions.system;

import com.maukaim.bulo.io.definitions.system.dtos.DefinitionEventType;

import java.time.Instant;

public class ExecutorUpdateEvent {
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

    public DefinitionEventType getEventType() {
        return eventType;
    }

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
