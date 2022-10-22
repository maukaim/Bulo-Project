package com.maukaim.bulo.definitions.registry.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;

import java.time.Instant;

public class StageDefinitionInstructionEventMixIn {
    @JsonCreator
    public StageDefinitionInstructionEventMixIn(@JsonProperty("stageExecutorId") String stageExecutorId,
                                                @JsonProperty("stageDefinition") StageDefinitionDto stageDefinition,
                                                @JsonProperty("instant") Instant instant) {
    }
}
