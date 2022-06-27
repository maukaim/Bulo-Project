package com.maukaim.boule.flows.orchestrator.stage.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.boule.flows.orchestrator.stage.StageEvent;

public class AcknowledgeRequestStageEvent implements StageEvent {
    private final String executorId;
    private final String stageId;
    private final String flowRunId;

    public AcknowledgeRequestStageEvent(@JsonProperty("executorId") String executorId,
                                        @JsonProperty("stageId") String stageId,
                                        @JsonProperty("flowRunId") String flowRunId) {
        this.executorId = executorId;
        this.stageId = stageId;
        this.flowRunId = flowRunId;
    }

    @Override
    public StageEventType getEventType() {
        return StageEventType.ACKNOWLEDGE_REQUEST;
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

    @Override
    public String toString() {
        return "AcknowledgeRequestStageEvent{" +
                "executorId='" + executorId + '\'' +
                ", stageId='" + stageId + '\'' +
                ", flowRunId='" + flowRunId + '\'' +
                '}';
    }
}
