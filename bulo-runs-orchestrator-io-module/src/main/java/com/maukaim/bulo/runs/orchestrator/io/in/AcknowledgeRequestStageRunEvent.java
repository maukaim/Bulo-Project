package com.maukaim.bulo.runs.orchestrator.io.in;

import java.time.Instant;

public final class AcknowledgeRequestStageRunEvent extends BasicStageRunEvent {
    private final String executorId;

    public AcknowledgeRequestStageRunEvent(String executorId,
                                           String stageRunId,
                                           Instant instant) {
        super(stageRunId, instant);
        this.executorId = executorId;
    }

    @Override
    public StageRunEventType getEventType() {
        return StageRunEventType.ACKNOWLEDGE_REQUEST;
    }

    public String getExecutorId() {
        return executorId;
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
