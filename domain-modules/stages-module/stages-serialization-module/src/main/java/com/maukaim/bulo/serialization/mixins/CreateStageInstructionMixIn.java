package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.stages.models.stages.StageDto;

import java.time.Instant;

public class CreateStageInstructionMixIn {
    @JsonCreator
    public CreateStageInstructionMixIn(
            @JsonProperty("stage") StageDto stageDto,
            @JsonProperty("instant") Instant instant) {
    }
}
