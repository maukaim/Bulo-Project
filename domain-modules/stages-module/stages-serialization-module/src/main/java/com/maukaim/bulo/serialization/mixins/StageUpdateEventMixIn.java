package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.stages.client.model.StageDto;
import com.maukaim.bulo.io.stages.system.models.stages.StageUpdateEventType;

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
