package com.maukaim.bulo.definitions.registry.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.shared.DefinitionEventType;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageDefinitionDto;

import java.time.Instant;

public class StageDefinitionEventMixIn {
    @JsonCreator
    public StageDefinitionEventMixIn(@JsonProperty("stageDefinition") StageDefinitionDto technicalStageDefinition,
                                     @JsonProperty("eventType") DefinitionEventType eventType,
                                     @JsonProperty("instant") Instant instant) {
    }
}
