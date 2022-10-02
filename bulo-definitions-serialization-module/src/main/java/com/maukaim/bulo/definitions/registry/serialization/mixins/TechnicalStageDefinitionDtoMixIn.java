package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.commons.io.instructions.models.StageOutputDefinitionDto;

import java.util.List;
import java.util.Map;

public class TechnicalStageDefinitionDtoMixIn {
    @JsonCreator
    public TechnicalStageDefinitionDtoMixIn(@JsonProperty("technicalStageDefinitionId") String technicalStageDefinitionId,
                                            @JsonProperty("inputsByName") Map<String, StageInputDefinitionDto> inputsByName,
                                            @JsonProperty("outputsByName") Map<String, StageOutputDefinitionDto> outputsByName,
                                            @JsonProperty("parameters") List<ParameterDefinitionDto> parameters) {
    }
}
