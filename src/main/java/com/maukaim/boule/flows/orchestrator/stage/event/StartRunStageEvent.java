package com.maukaim.boule.flows.orchestrator.stage.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.boule.flows.orchestrator.stage.StageEvent;

public class StartRunStageEvent implements StageEvent {
    private final String executorId;
    private final String stageId;
    private final String flowRunId;

    private final String stageRunId;

    public StartRunStageEvent(@JsonProperty("executorId") String executorId,
                              @JsonProperty("stageId") String stageId,
                              @JsonProperty("flowRunId") String flowRunId,
                              @JsonProperty("stageRunId") String stageRunId) {
        this.executorId = executorId;
        this.stageId = stageId;
        this.flowRunId = flowRunId;
        this.stageRunId = stageRunId;
    }

    @Override
    public StageEventType getEventType() {
        return StageEventType.LAUNCH_RUN;
    }

    @Override
    public String getStageId() {
        return stageId;
    }

    @Override
    public String getFlowRunId() {
        return flowRunId;
    }

    public String getExecutorId() {
        return executorId;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    @Override
    public String toString() {
        return "StartRunStageEvent{" +
                "executorId='" + executorId + '\'' +
                ", stageId='" + stageId + '\'' +
                ", flowRunId='" + flowRunId + '\'' +
                ", stageRunId='" + stageRunId + '\'' +
                '}';
    }
}
