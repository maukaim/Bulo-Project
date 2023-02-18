package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.system.events.model.DefinitionEventType;
import com.maukaim.bulo.io.definitions.client.models.StageDefinitionDto;

import java.time.Instant;

public class StageDefinitionEventMixIn {
    @JsonCreator
    public StageDefinitionEventMixIn(@JsonProperty("stageDefinition") StageDefinitionDto technicalStageDefinition,
                                     @JsonProperty("eventType") DefinitionEventType eventType,
                                     @JsonProperty("instant") Instant instant) {
    }
}
