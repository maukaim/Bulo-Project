package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.stages.client.model.ParameterDto;

import java.util.List;

public class TechnicalStageDtoMixIn {
    @JsonCreator
    public TechnicalStageDtoMixIn(@JsonProperty("stageId") String stageId,
                                  @JsonProperty("parameters") List<ParameterDto> parameters,
                                  @JsonProperty("definitionId") String definitionId) {
    }
}
