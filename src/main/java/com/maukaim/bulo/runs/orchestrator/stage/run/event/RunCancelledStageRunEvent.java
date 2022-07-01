package com.maukaim.bulo.runs.orchestrator.stage.run.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RunCancelledStageRunEvent implements StageRunEvent {
    private final String stageRunId;

    public RunCancelledStageRunEvent(@JsonProperty("stageRunId") String stageRunId) {
        this.stageRunId = stageRunId;
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
    public String toString() {
        return "RunFailedStageEvent{" +
                "stageId='" + stageRunId + '\'' +
                '}';
    }
}
