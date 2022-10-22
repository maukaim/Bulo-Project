package com.maukaim.bulo.definitions.registry.serialization.mixins.stage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.definitions.io.stage.ParameterDto;

import java.util.List;

public class TechnicalStageDtoMixIn {
    @JsonCreator
    public TechnicalStageDtoMixIn(@JsonProperty("stageId") String stageId,
                                  @JsonProperty("parameters") List<ParameterDto> parameters,
                                  @JsonProperty("definitionId") String definitionId) {
    }
}
