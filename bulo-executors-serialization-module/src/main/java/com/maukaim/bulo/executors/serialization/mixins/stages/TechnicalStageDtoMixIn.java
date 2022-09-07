package com.maukaim.bulo.executors.serialization.mixins.stages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.executors.io.in.model.ParameterDto;

import java.util.List;

public class TechnicalStageDtoMixIn {
    @JsonCreator
    public TechnicalStageDtoMixIn(@JsonProperty("parameters") List<ParameterDto> parameters,
                                  @JsonProperty("definitionId") String definitionId) {
    }
}
