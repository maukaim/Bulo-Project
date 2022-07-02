package com.maukaim.bulo.runs.orchestrator.stage.run.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class StartRunStageRunEvent implements StageRunEvent {
    private final String stageRunId;
    private final Instant instant;

    public StartRunStageRunEvent(@JsonProperty("stageRunId") String stageRunId, @JsonProperty("instant") Instant instant) {
        this.stageRunId = stageRunId;
        this.instant = instant;
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.LAUNCH_RUN;
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
        return "StartRunStageRunEvent{" +
                "stageRunId='" + stageRunId + '\'' +
                ", instant=" + instant +
                '}';
    }

}
