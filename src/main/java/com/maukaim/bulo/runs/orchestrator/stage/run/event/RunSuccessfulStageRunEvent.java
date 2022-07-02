package com.maukaim.bulo.runs.orchestrator.stage.run.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class RunSuccessfulStageRunEvent implements StageRunEvent {
    private final String stageRunId;
    private final Instant instant;

    public RunSuccessfulStageRunEvent(@JsonProperty("stageRunId") String stageRunId, @JsonProperty("instant") Instant instant) {
        this.stageRunId = stageRunId;
        this.instant = instant;
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_SUCCESSFUL;
    }

    @Override
    public String getStageRunId() {
        return stageRunId;
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "RunSuccessfulStageRunEvent{" +
                "stageRunId='" + stageRunId + '\'' +
                ", instant=" + instant +
                '}';
    }
}
