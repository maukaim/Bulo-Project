package com.maukaim.bulo.serialization.stages.client.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.stages.client.model.StageDto;

import java.time.Instant;

public class CreateStageInstructionMixIn {
    @JsonCreator
    public CreateStageInstructionMixIn(
            @JsonProperty("stage") StageDto stageDto,
            @JsonProperty("instant") Instant instant) {
    }
}
