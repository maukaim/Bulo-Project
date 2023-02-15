package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.io.shared.ExternalEvent;
import com.maukaim.bulo.io.stages.shared.StageUpdateEventType;
import com.maukaim.bulo.runs.orchestrators.io.models.stage.StageDto;

import java.time.Instant;

public class StageUpdateEvent implements ExternalEvent {
    private String stageId;
    private StageDto stage;
    private StageUpdateEventType eventType;
    private Instant instant;

    public StageUpdateEvent(String stageId, StageDto stage, StageUpdateEventType eventType, Instant instant) {
        this.stageId = stageId;
        this.eventType = eventType;
        this.instant = instant;
        this.stage = stage;
    }

    public String getStageId() {
        return stageId;
    }

    public StageDto getStage() {
        return stage;
    }

    public StageUpdateEventType getEventType() {
        return eventType;
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
