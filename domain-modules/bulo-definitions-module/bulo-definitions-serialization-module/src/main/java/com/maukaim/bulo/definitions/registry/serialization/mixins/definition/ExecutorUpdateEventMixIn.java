package com.maukaim.bulo.definitions.registry.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.DefinitionEventType;

import java.time.Instant;

public class ExecutorUpdateEventMixIn {
    @JsonCreator
    public ExecutorUpdateEventMixIn(
            @JsonProperty("executorId") String executorId,
            @JsonProperty("definitionId") String definitionId,
            @JsonProperty("eventType") DefinitionEventType eventType,
            @JsonProperty("instant") Instant instant) {
    }
}
