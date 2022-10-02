package com.maukaim.bulo.executors.io.in;

import com.maukaim.bulo.commons.io.ExternalEvent;

import java.time.Instant;

public class CancelRunInstruction implements ExternalEvent {
    private String stageRunId;
    private Instant instant;

    public CancelRunInstruction(String stageRunId, Instant instant) {
        this.stageRunId = stageRunId;
        this.instant = instant;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "NeedStageRunCancelEvent{" +
                "stageRunId='" + stageRunId + '\'' +
                ", instant=" + instant +
                '}';
    }
}
