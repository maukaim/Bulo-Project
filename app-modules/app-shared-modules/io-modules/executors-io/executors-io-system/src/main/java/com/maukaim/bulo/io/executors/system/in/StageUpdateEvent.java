package com.maukaim.bulo.io.executors.system.in;

import com.maukaim.bulo.io.executors.system.in.model.StageDto;
import com.maukaim.bulo.io.executors.system.in.model.StageUpdateEventType;
import com.maukaim.bulo.io.executors.system.in.model.TechnicalStageDto;
import com.maukaim.bulo.io.shared.ExternalEvent;

import java.time.Instant;

public class StageUpdateEvent implements ExternalEvent {
    private String stageId;
    private StageUpdateEventType eventType;
    private StageDto stage;
    private Instant instant;

    public StageUpdateEvent(String stageId, TechnicalStageDto stage, StageUpdateEventType eventType, Instant instant) {
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
