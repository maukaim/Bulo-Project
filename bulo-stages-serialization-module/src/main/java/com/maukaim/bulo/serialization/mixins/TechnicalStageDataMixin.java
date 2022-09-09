package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.stages.io.models.stages.ParameterDto;

import java.util.List;

public class TechnicalStageDataMixin {
    @JsonCreator
    public TechnicalStageDataMixin(@JsonProperty("parameters") List<ParameterDto> parameters,
                                   @JsonProperty("definitionId") String definitionId) {
    }
}
