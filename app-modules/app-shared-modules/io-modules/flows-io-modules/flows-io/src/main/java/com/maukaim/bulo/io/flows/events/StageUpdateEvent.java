package com.maukaim.bulo.io.flows.events;

import com.maukaim.bulo.io.flows.stage.StageDto;
import com.maukaim.bulo.io.flows.stage.StageUpdateEventType;
import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public class StageUpdateEvent implements ExternalEvent {
    private String stageId;
    private StageDto stage;
    private StageUpdateEventType eventType;
    private Instant instant;

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
