package com.maukaim.bulo.executor.io.out;

import com.maukaim.bulo.commons.io.StageRunEventType;

import java.time.Instant;

public final class AcknowledgeStageRunEvent extends StageRunEvent {
    private final String executorId;

    public AcknowledgeStageRunEvent(String executorId,
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
