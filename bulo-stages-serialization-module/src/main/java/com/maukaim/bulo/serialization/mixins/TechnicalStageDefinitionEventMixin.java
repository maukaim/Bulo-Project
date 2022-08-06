package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.io.definitions.TechnicalStageDefinitionData;

import java.time.Instant;

public class TechnicalStageDefinitionEventMixin {
    @JsonCreator
    public TechnicalStageDefinitionEventMixin(
            @JsonProperty("technicalStageDefinition") TechnicalStageDefinitionData technicalStageDefinition,
            @JsonProperty("eventType") DefinitionEventType eventType,
            @JsonProperty("instant") Instant instant) {
    }
}
