package com.maukaim.boule.flows.orchestrator.stage.run.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RunFailedStageRunEvent implements StageRunEvent {
    private final String stageRunId;

    public RunFailedStageRunEvent(@JsonProperty("stageRunId") String stageRunId) {
        this.stageRunId = stageRunId;
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_FAILED;
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
