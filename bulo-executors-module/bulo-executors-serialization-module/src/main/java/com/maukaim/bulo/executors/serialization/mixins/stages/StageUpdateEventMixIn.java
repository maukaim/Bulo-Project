package com.maukaim.bulo.executors.serialization.mixins.stages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.StageUpdateEventType;
import com.maukaim.bulo.executors.io.in.model.StageDto;
import com.maukaim.bulo.executors.io.in.model.TechnicalStageDto;

import java.time.Instant;

public class StageUpdateEventMixIn {
    @JsonCreator
    public StageUpdateEventMixIn(
            @JsonProperty("stageId") String stageId,
            @JsonProperty("stage") TechnicalStageDto stage,
            @JsonProperty("eventType") StageUpdateEventType eventType,
            @JsonProperty("instant") Instant instant) {
    }
}
