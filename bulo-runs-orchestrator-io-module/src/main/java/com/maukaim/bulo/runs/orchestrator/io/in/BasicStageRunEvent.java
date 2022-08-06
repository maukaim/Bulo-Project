package com.maukaim.bulo.runs.orchestrator.io.in;

import com.maukaim.bulo.commons.io.IStageRunEvent;

import java.time.Instant;

public abstract class BasicStageRunEvent implements IStageRunEvent {
    protected final String stageRunId;
    protected final Instant instant;

    public BasicStageRunEvent(String stageRunId, Instant instant) {
        this.stageRunId = stageRunId;
        this.instant = instant;
    }

    @Override
    public String getStageRunId() {
        return stageRunId;
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
                '}';
    }
}
