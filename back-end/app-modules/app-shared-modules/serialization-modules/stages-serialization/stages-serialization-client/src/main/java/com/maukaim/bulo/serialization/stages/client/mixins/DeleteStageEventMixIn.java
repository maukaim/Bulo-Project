package com.maukaim.bulo.serialization.stages.client.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class DeleteStageEventMixIn {
    @JsonCreator
    public DeleteStageEventMixIn(
            @JsonProperty("stageId") String stageId,
            @JsonProperty("instant") Instant instant) {
    }
}
