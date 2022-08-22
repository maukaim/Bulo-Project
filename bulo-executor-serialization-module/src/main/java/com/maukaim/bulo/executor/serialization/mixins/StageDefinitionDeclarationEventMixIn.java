package com.maukaim.bulo.executor.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.executor.io.out.model.StageDefinitionDto;

import java.time.Instant;

public class StageDefinitionDeclarationEventMixIn {
    @JsonCreator
    public StageDefinitionDeclarationEventMixIn(
            @JsonProperty("technicalStageDefinition") StageDefinitionDto technicalStageDefinition,
            @JsonProperty("eventType") DefinitionEventType eventType,
            @JsonProperty("instant") Instant instant) {
    }
}
