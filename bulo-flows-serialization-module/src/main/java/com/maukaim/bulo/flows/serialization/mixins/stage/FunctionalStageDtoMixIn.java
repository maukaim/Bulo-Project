package com.maukaim.bulo.flows.serialization.mixins.stage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.flows.io.stage.ParameterDto;

import java.util.List;

public class FunctionalStageDtoMixIn {
    @JsonCreator
    public FunctionalStageDtoMixIn(@JsonProperty("stageId") String stageId,
                                   @JsonProperty("parameters") List<ParameterDto> parameters) {
    }
}
