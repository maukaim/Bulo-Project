package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public abstract class StageRunEvent implements ExternalEvent {

    protected final String stageRunId;
    protected final Instant instant;

    public StageRunEvent(String stageRunId, Instant instant) {
        this.stageRunId = stageRunId;
        this.instant = instant;
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
