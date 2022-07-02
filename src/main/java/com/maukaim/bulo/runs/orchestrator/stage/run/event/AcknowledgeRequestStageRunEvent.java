package com.maukaim.bulo.runs.orchestrator.stage.run.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public final class AcknowledgeRequestStageRunEvent implements StageRunEvent {
    private final String executorId;
    private final String stageRunId;
    private final Instant instant;

    public AcknowledgeRequestStageRunEvent(@JsonProperty("executorId") String executorId,
                                           @JsonProperty("stageRunId") String stageRunId,
                                           @JsonProperty("instant") Instant instant) {
        this.executorId = executorId;
        this.stageRunId = stageRunId;
        this.instant = instant;
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
    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "AcknowledgeRequestStageRunEvent{" +
                "executorId='" + executorId + '\'' +
                ", stageRunId='" + stageRunId + '\'' +
                ", instant=" + instant +
                '}';
    }
}
