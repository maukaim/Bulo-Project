package com.maukaim.bulo.serialization.definitions.system.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.system.dtos.DefinitionEventType;

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
