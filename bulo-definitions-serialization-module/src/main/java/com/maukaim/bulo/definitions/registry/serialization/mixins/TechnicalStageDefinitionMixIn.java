package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.definitions.io.models.ParameterDefinitionDto;
import com.maukaim.bulo.definitions.io.models.StageInputDefinitionDto;
import com.maukaim.bulo.definitions.io.models.StageOutputDefinitionDto;

import java.util.List;
import java.util.Map;

public class TechnicalStageDefinitionMixIn {
    @JsonCreator
    public TechnicalStageDefinitionMixIn(@JsonProperty("technicalStageDefinitionId") String technicalStageDefinitionId,
                                         @JsonProperty("inputsByName") Map<String, StageInputDefinitionDto> inputsByName,
                                         @JsonProperty("outputsByName") Map<String, StageOutputDefinitionDto> outputsByName,
                                         @JsonProperty("parameters") List<ParameterDefinitionDto> parameters) {
    }
}
