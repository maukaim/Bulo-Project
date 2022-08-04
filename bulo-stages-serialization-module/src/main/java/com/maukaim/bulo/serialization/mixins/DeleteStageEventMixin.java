package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.stages.StageData;

import java.time.Instant;

public class DeleteStageEventMixin {
    @JsonCreator
    public DeleteStageEventMixin(
            @JsonProperty("stageId") String stageId,
            @JsonProperty("instant") Instant instant){}
}
