package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.StageUpdateEventType;
import com.maukaim.bulo.stages.io.models.stages.StageDto;

import java.time.Instant;

public class StageUpdateEventMixIn {
    @JsonCreator
    public StageUpdateEventMixIn(
            @JsonProperty("stageId") String stageId,
            @JsonProperty("stage") StageDto stage,
            @JsonProperty("eventType") StageUpdateEventType eventType,
            @JsonProperty("instant") Instant instant) {
    }
}
