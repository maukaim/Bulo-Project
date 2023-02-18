package com.maukaim.bulo.definitions.registry.serialization.mixins.definition.technical;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.client.models.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.client.models.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.models.StageOutputDefinitionDto;

import java.util.List;
import java.util.Map;

public class TechnicalStageDefinitionDtoMixIn {
    @JsonCreator
    public TechnicalStageDefinitionDtoMixIn(@JsonProperty("definitionId") String definitionId,
                                            @JsonProperty("inputsByName") Map<String, StageInputDefinitionDto> inputsByName,
                                            @JsonProperty("outputsByName") Map<String, StageOutputDefinitionDto> outputsByName,
                                            @JsonProperty("parameters") List<ParameterDefinitionDto> parameters) {
    }
}
