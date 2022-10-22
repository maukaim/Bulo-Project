package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.stages.io.models.stages.StageDto;

import java.time.Instant;

public class CreateStageInstructionMixin {
    @JsonCreator
    public CreateStageInstructionMixin(
            @JsonProperty("stage") StageDto stageDto,
            @JsonProperty("instant") Instant instant) {
    }
}
