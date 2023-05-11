package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.executors.system.dtos.StageRunEventType;
import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public abstract class StageRunEvent implements ExternalEvent {

    protected final String stageRunId;
    protected final Instant instant;
    protected final StageRunEventType eventType;

    public StageRunEvent(String stageRunId, StageRunEventType eventType, Instant instant) {
        this.stageRunId = stageRunId;
        this.eventType = eventType;
        this.instant = instant;
    }

    public StageRunEventType getEventType(){
        return eventType;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "stageRunId='" + stageRunId + '\'' +
                ", instant=" + instant +
                '}';
    }
}
