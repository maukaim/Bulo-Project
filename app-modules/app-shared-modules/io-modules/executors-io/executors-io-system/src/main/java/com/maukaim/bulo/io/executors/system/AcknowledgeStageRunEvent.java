package com.maukaim.bulo.io.executors.system;

import com.maukaim.bulo.io.executors.system.dtos.StageRunEventType;

import java.time.Instant;

public class AcknowledgeStageRunEvent extends StageRunEvent {
    private final String executorId;

    public AcknowledgeStageRunEvent(String executorId,
                                    String stageRunId,
                                    Instant instant) {
        super(stageRunId, StageRunEventType.ACKNOWLEDGE_REQUEST, instant);
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
