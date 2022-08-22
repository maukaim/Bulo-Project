package com.maukaim.bulo.executor.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.StageUpdateEventType;
import com.maukaim.bulo.executor.io.in.model.StageDto;

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
