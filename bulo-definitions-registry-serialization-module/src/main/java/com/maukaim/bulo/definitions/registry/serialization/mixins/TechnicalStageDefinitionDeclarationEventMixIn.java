package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;

import java.time.Instant;

public class TechnicalStageDefinitionDeclarationEventMixIn {
    @JsonCreator
    public TechnicalStageDefinitionDeclarationEventMixIn(@JsonProperty("stageExecutorId") String stageExecutorId,
                                                         @JsonProperty("technicalStageDefinition") TechnicalStageDefinition technicalStageDefinition,
                                                         @JsonProperty("instant") Instant instant) {
    }
}
