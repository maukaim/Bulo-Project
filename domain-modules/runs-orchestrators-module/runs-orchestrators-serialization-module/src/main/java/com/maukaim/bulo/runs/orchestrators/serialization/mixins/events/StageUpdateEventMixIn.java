package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stage.StageDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stage.StageUpdateEventType;

import java.time.Instant;

public class StageUpdateEventMixIn {
    @JsonCreator
    public StageUpdateEventMixIn(@JsonProperty("stageId") String stageId,
                                 @JsonProperty("stage") StageDto stageDto,
                                 @JsonProperty("eventType") StageUpdateEventType eventType,
                                 @JsonProperty("instant") Instant instant) {

    }

}
