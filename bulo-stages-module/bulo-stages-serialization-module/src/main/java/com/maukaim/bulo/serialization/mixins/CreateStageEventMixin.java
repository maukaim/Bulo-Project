package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.stages.io.models.stages.StageDto;

import java.time.Instant;

public class CreateStageEventMixin {
    @JsonCreator
    public CreateStageEventMixin(
            @JsonProperty("stageData") StageDto stageDto,
            @JsonProperty("instant") Instant instant) {
    }
}
