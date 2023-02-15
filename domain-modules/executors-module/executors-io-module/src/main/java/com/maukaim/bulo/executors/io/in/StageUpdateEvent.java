package com.maukaim.bulo.executors.io.in;

import com.maukaim.bulo.io.shared.ExternalEvent;
import com.maukaim.bulo.io.stages.shared.StageUpdateEventType;
import com.maukaim.bulo.executors.io.in.model.StageDto;
import com.maukaim.bulo.executors.io.in.model.TechnicalStageDto;

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
