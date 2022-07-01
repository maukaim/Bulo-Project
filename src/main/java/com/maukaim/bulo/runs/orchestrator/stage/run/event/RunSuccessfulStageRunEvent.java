package com.maukaim.bulo.runs.orchestrator.stage.run.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RunSuccessfulStageRunEvent implements StageRunEvent {
    private final String stageRunId;

    public RunSuccessfulStageRunEvent(@JsonProperty("stageRunId") String stageRunId) {
        this.stageRunId = stageRunId;
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.RUN_SUCCESSFUL;
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
