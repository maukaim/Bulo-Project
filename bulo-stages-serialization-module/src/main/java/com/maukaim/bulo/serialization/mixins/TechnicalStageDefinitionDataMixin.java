package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.stages.io.models.definitions.ParameterDefinitionDto;

import java.util.List;

public class TechnicalStageDefinitionDataMixin {
    @JsonCreator
    public TechnicalStageDefinitionDataMixin(@JsonProperty("id") String technicalStageDefinitionId,
                                             @JsonProperty("parameters") List<ParameterDefinitionDto> parameters) {
    }
}
