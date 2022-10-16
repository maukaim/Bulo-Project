package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.commons.io.IStageRunEvent;
import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public abstract class BasicStageRunEvent implements IStageRunEvent {
    protected final String stageRunId;
    protected final Instant instant;
    protected final StageRunEventType eventType;

    public BasicStageRunEvent(String stageRunId, Instant instant, StageRunEventType eventType) {
        this.stageRunId = stageRunId;
        this.instant = instant;
        this.eventType = eventType;
    }

    @Override
    public String getStageRunId() {
        return stageRunId;
    }

    @Override
    public StageRunEventType getEventType(){
        return eventType;
    }

    @Override
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
