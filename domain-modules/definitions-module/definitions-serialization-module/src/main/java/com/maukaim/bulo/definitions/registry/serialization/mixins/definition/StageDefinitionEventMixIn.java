package com.maukaim.bulo.definitions.registry.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.technical.TechnicalStageDefinitionDto;

import java.time.Instant;

public class StageDefinitionEventMixIn {
    @JsonCreator
    public StageDefinitionEventMixIn(@JsonProperty("stageDefinition") StageDefinitionDto technicalStageDefinition,
                                     @JsonProperty("eventType") DefinitionEventType eventType,
                                     @JsonProperty("instant") Instant instant) {
    }
}
