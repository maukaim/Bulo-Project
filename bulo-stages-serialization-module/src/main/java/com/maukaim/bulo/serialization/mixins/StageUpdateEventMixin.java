package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.StageUpdateEventType;
import com.maukaim.bulo.io.stages.StageData;

import java.time.Instant;

public class StageUpdateEventMixin {
    @JsonCreator
    public StageUpdateEventMixin(
            @JsonProperty("stageId") String stageId,
            @JsonProperty("stage") StageData stage,
            @JsonProperty("eventType") StageUpdateEventType eventType,
            @JsonProperty("instant") Instant instant) {
    }
}
