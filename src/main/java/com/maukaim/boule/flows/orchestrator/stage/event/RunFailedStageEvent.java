package com.maukaim.boule.flows.orchestrator.stage.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.boule.flows.orchestrator.stage.StageEvent;

public class RunFailedStageEvent implements StageEvent {
    private final String stageId;
    private final String flowRunId;

    public RunFailedStageEvent(@JsonProperty("stageId") String stageId,
                               @JsonProperty("flowRunId") String flowRunId) {
        this.stageId = stageId;
        this.flowRunId = flowRunId;
    }

    @Override
    public StageEventType getEventType() {
        return StageEventType.RUN_FAILED;
    }

    @Override
    public String getStageId() {
        return stageId;
    }

    @Override
    public String getFlowRunId() {
        return flowRunId;
    }

    @Override
    public String toString() {
        return "RunFailedStageEvent{" +
                "stageId='" + stageId + '\'' +
                ", flowRunId='" + flowRunId + '\'' +
                '}';
    }
}
