package com.maukaim.bulo.io.runs.orchestrators.events;

import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public abstract class BasicStageRunEvent implements ExternalEvent {
    protected final String stageRunId;
    protected final Instant instant;
    protected final StageRunEventType eventType;

    public BasicStageRunEvent(String stageRunId, Instant instant, StageRunEventType eventType) {
        this.stageRunId = stageRunId;
        this.instant = instant;
        this.eventType = eventType;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public StageRunEventType getEventType() {
        return eventType;
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "stageRunId='" + stageRunId + '\'' +
                ", instant=" + instant +
                ", eventType=" + eventType +
                '}';
    }
}
