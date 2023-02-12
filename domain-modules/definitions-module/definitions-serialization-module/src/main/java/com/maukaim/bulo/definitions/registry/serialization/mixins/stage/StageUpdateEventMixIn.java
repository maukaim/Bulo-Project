package com.maukaim.bulo.definitions.registry.serialization.mixins.stage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.StageUpdateEventType;
import com.maukaim.bulo.definitions.io.stage.StageDto;

import java.time.Instant;

public class StageUpdateEventMixIn {
    @JsonCreator
    public StageUpdateEventMixIn(@JsonProperty("stageId") String stageId,
                                 @JsonProperty("stage") StageDto stage,
                                 @JsonProperty("eventType") StageUpdateEventType eventType,
                                 @JsonProperty("instant") Instant instant){}
}
