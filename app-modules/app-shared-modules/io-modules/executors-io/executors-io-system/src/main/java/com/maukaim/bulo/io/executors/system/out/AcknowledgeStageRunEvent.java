package com.maukaim.bulo.io.executors.system.out;

import com.maukaim.bulo.io.executors.system.out.model.StageRunEventType;

import java.time.Instant;

public class AcknowledgeStageRunEvent extends StageRunEvent {
    private final String executorId;

    public AcknowledgeStageRunEvent(String executorId,
                                    String stageRunId,
                                    Instant instant) {
        super(stageRunId, instant);
        this.executorId = executorId;
    }

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
