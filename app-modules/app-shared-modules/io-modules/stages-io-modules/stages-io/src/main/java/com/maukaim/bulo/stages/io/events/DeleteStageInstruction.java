package com.maukaim.bulo.stages.io.events;

import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public class DeleteStageInstruction implements ExternalEvent {
    private final Instant instant;
    private final String stageId;

    public DeleteStageInstruction(String stageId, Instant instant) {
        this.instant = instant;
        this.stageId = stageId;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public String getStageId() {
        return stageId;
    }

    @Override
    public String toString() {
        return "DeleteStageEvent{" +
                "instant=" + instant +
                ", stageId='" + stageId + '\'' +
                '}';
    }
}
