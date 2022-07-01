package com.maukaim.bulo.runs.orchestrator.stage.run.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class AcknowledgeRequestStageRunEvent implements StageRunEvent {
    private final String executorId;
    private final String stageRunId;

    public AcknowledgeRequestStageRunEvent(@JsonProperty("executorId") String executorId,
                                           @JsonProperty("stageRunId") String stageRunId) {
        this.executorId = executorId;
        this.stageRunId = stageRunId;
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.ACKNOWLEDGE_REQUEST;
    }

    @Override
    public String getStageRunId() {
        return stageRunId;
    }

    public String getExecutorId() {
        return executorId;
    }

    @Override
    public String toString() {
        return "AcknowledgeRequestStageEvent{" +
                "executorId='" + executorId + '\'' +
                ", stageId='" + stageRunId + '\'' +
                '}';
    }
}
