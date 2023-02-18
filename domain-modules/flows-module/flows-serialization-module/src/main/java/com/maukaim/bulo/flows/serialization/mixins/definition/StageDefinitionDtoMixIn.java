package com.maukaim.bulo.flows.serialization.mixins.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.flows.system.definition.ParameterDefinitionDto;
import com.maukaim.bulo.io.flows.system.definition.StageInputDefinitionDto;
import com.maukaim.bulo.io.flows.system.definition.StageOutputDefinitionDto;

import java.util.List;
import java.util.Map;

public class StageDefinitionDtoMixIn {
    @JsonCreator
    public StageDefinitionDtoMixIn(@JsonProperty("definitionId") String definitionId,
                                   @JsonProperty("inputsByName") Map<String, StageInputDefinitionDto> inputsByName,
                                   @JsonProperty("outputsByName") Map<String, StageOutputDefinitionDto> outputsByName,
                                   @JsonProperty("parameters") List<ParameterDefinitionDto> parameters) {
    }
}
