package com.maukaim.bulo.runs.orchestrator.io.in;

import java.time.Instant;

public abstract class BasicStageRunEvent implements StageRunEvent{
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
