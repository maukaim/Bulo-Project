package com.maukaim.bulo.executors.serialization.mixins.stages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.executors.system.in.model.ParameterDto;

import java.util.List;

public class TechnicalStageDtoMixIn {
    @JsonCreator
    public TechnicalStageDtoMixIn(@JsonProperty("stageId") String stageId,
                                  @JsonProperty("parameters") List<ParameterDto> parameters,
                                  @JsonProperty("definitionId") String definitionId) {
    }
}
