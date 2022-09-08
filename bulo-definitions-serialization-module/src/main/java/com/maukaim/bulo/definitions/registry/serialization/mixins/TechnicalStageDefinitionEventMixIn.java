package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.definitions.io.models.TechnicalStageDefinitionDto;

import java.time.Instant;

public class TechnicalStageDefinitionEventMixIn {
    @JsonCreator
    public TechnicalStageDefinitionEventMixIn(@JsonProperty("stageExecutorId") TechnicalStageDefinitionDto technicalStageDefinition,
                                              @JsonProperty("eventType") DefinitionEventType eventType,
                                              @JsonProperty("instant") Instant instant) {
    }
}
