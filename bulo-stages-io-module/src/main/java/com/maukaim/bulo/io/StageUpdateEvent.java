package com.maukaim.bulo.io;

import com.maukaim.bulo.commons.io.ExternalEvent;
import com.maukaim.bulo.commons.io.StageUpdateEventType;
import com.maukaim.bulo.io.stages.StageData;

import java.time.Instant;

public class StageUpdateEvent implements ExternalEvent {
    private String stageId;
    private StageData stage;
    private StageUpdateEventType eventType;
    private Instant instant;

    public StageUpdateEvent(String stageId, StageData stage, StageUpdateEventType eventType, Instant instant) {
        this.stageId = stageId;
        this.stage = stage;
        this.eventType = eventType;
        this.instant = instant;
    }

    public String getStageId() {
        return stageId;
    }

    public StageUpdateEventType getEventType() {
        return eventType;
    }

    public StageData getStage() {
        return stage;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        return "StageUpdateEvent{" +
                "stageId='" + stageId + '\'' +
                ", stage=" + stage +
                ", eventType=" + eventType +
                ", instant=" + instant +
                '}';
    }
}
