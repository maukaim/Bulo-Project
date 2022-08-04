package com.maukaim.bulo.io;

import com.maukaim.bulo.commons.io.ExternalEvent;

import java.time.Instant;

public class DeleteStageEvent implements ExternalEvent {
    private final Instant instant;
    private final String stageId;

    public DeleteStageEvent(String stageId, Instant instant) {
        this.instant = instant;
        this.stageId = stageId;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    public String getStageId(){
        return stageId;
    }

    @Override
    public String toString() {
        return "DeleteStageEvent{" +
                "instant=" + instant +
                ", globalStageId='" + stageId + '\'' +
                '}';
    }
}
