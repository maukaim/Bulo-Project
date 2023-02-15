package com.maukaim.bulo.executors.serialization.mixins.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.shared.DefinitionEventType;
import com.maukaim.bulo.io.definitions.shared.instructions.models.technical.TechnicalStageDefinitionDto;

import java.time.Instant;

public class StageDefinitionCreateInstructionMixIn {
    @JsonCreator
    public StageDefinitionCreateInstructionMixIn(
            @JsonProperty("technicalStageDefinition") TechnicalStageDefinitionDto technicalStageDefinition,
            @JsonProperty("eventType") DefinitionEventType eventType,
            @JsonProperty("instant") Instant instant) {
    }
}
