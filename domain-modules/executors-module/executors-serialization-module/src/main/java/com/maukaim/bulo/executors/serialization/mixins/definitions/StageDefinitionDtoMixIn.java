package com.maukaim.bulo.executors.serialization.mixins.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.shared.instructions.models.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageOutputDefinitionDto;


import java.util.List;
import java.util.Map;

public class StageDefinitionDtoMixIn {
    @JsonCreator
    public StageDefinitionDtoMixIn(
            @JsonProperty("definitionId") String technicalStageDefinitionId,
            @JsonProperty("inputsByName") Map<String, StageInputDefinitionDto> inputsByName,
            @JsonProperty("outputsByName") Map<String, StageOutputDefinitionDto> outputsByName,
            @JsonProperty("parameters") List<ParameterDefinitionDto> parameters) {
    }
}