package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public class NeedStageRunCancellationEvent implements ExternalEvent {
    private String stageRunId;
    private String executorId;
    private Instant instant;

    public NeedStageRunCancellationEvent(String stageRunId, String executorId, Instant instant) {
        this.stageRunId = stageRunId;
        this.executorId = executorId;
        this.instant = instant;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public String getExecutorId() {
        return executorId;
    }

    @Override
    public String toString() {
        return "NeedStageRunCancellationEvent{" +
                "stageRunId='" + stageRunId + '\'' +
                ", executorId='" + executorId + '\'' +
                ", instant=" + instant +
                '}';
    }
}
