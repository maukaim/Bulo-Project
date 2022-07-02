package com.maukaim.bulo.runs.orchestrator.stage.run.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class RunCancelledStageRunEvent implements StageRunEvent {
    private final String stageRunId;
    private final Instant instant;

    public RunCancelledStageRunEvent(@JsonProperty("stageRunId") String stageRunId, @JsonProperty("instant") Instant instant) {
        this.stageRunId = stageRunId;
        this.instant = instant;
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_CANCELLED;
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
        return "RunCancelledStageRunEvent{" +
                "stageRunId='" + stageRunId + '\'' +
                ", instant=" + instant +
                '}';
    }
}
