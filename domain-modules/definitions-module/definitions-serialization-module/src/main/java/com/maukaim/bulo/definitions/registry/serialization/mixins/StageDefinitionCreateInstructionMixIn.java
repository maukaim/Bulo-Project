package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.client.models.StageDefinitionDto;

import java.time.Instant;

public class StageDefinitionCreateInstructionMixIn {
    @JsonCreator
    public StageDefinitionCreateInstructionMixIn(@JsonProperty("stageExecutorId") String stageExecutorId,
                                                 @JsonProperty("stageDefinition") StageDefinitionDto stageDefinition,
                                                 @JsonProperty("instant") Instant instant) {
    }
}
