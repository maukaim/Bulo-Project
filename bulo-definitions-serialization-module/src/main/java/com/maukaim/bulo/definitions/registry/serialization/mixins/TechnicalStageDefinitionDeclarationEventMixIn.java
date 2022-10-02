package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.instructions.models.TechnicalStageDefinitionDto;

import java.time.Instant;

public class TechnicalStageDefinitionDeclarationEventMixIn {
    @JsonCreator
    public TechnicalStageDefinitionDeclarationEventMixIn(@JsonProperty("stageExecutorId") String stageExecutorId,
                                                         @JsonProperty("technicalStageDefinition") TechnicalStageDefinitionDto technicalStageDefinition,
                                                         @JsonProperty("instant") Instant instant) {
    }
}
