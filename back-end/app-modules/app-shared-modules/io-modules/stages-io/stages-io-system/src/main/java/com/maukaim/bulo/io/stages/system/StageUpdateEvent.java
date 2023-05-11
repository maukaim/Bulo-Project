package com.maukaim.bulo.io.stages.system;

import com.maukaim.bulo.io.shared.ExternalEvent;
import com.maukaim.bulo.io.stages.client.model.StageDto;

import java.time.Instant;

public class StageUpdateEvent implements ExternalEvent {
    private final String stageId;
    private final StageDto stage;
    private final StageUpdateEventType eventType;
    private final Instant instant;

    public StageUpdateEvent(String stageId, StageDto stage, StageUpdateEventType eventType, Instant instant) {
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

    public StageDto getStage() {
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
