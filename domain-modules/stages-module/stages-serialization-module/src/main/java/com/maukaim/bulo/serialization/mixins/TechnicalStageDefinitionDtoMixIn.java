package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.stages.models.definitions.ParameterDefinitionDto;

import java.util.List;

public class TechnicalStageDefinitionDtoMixIn {
    @JsonCreator
    public TechnicalStageDefinitionDtoMixIn(@JsonProperty("definitionId") String definitionId,
                                            @JsonProperty("parameters") List<ParameterDefinitionDto> parameters) {
    }
}
