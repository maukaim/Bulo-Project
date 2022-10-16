package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.stages.io.models.definitions.ParameterDefinitionDto;

import java.util.List;

public class TechnicalStageDefinitionDtoMixin {
    @JsonCreator
    public TechnicalStageDefinitionDtoMixin(@JsonProperty("technicalStageDefinitionId") String technicalStageDefinitionId,
                                            @JsonProperty("parameters") List<ParameterDefinitionDto> parameters) {
    }
}
