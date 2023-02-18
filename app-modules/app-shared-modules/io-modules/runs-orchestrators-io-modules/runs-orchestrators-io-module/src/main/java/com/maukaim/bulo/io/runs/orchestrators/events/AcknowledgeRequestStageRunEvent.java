package com.maukaim.bulo.io.runs.orchestrators.events;

import com.maukaim.bulo.io.runs.orchestrators.events.StageRunEventType;

import java.time.Instant;

public final class AcknowledgeRequestStageRunEvent extends BasicStageRunEvent {
    private final String executorId;

    public AcknowledgeRequestStageRunEvent(String executorId,
                                           String stageRunId,
                                           Instant instant) {
        super(stageRunId, instant, StageRunEventType.ACKNOWLEDGE_REQUEST);
        this.executorId = executorId;
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
