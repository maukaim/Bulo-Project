package com.maukaim.bulo.runs.orchestrator.stage.run.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartRunStageRunEvent implements StageRunEvent {
    private final String stageRunId;

    public StartRunStageRunEvent(@JsonProperty("stageRunId") String stageRunId) {
        this.stageRunId = stageRunId;
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.LAUNCH_RUN;
    }

    @Override
    public String getStageRunId() {
        return stageRunId;
    }

    @Override
    public String toString() {
        return "StartRunStageEvent{" +
                "stageRunId='" + stageRunId + '\'' +
                '}';
    }
}
