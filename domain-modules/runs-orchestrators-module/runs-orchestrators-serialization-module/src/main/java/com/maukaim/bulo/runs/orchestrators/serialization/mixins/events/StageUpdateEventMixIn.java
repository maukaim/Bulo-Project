package com.maukaim.bulo.runs.orchestrators.serialization.mixins.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.stages.shared.StageUpdateEventType;
import com.maukaim.bulo.io.runs.orchestrators.models.stage.StageDto;

import java.time.Instant;

public class StageUpdateEventMixIn {
    @JsonCreator
    public StageUpdateEventMixIn(@JsonProperty("stageId") String stageId,
                                 @JsonProperty("stage") StageDto stageDto,
                                 @JsonProperty("eventType") StageUpdateEventType eventType,
                                 @JsonProperty("instant") Instant instant) {

    }

}
