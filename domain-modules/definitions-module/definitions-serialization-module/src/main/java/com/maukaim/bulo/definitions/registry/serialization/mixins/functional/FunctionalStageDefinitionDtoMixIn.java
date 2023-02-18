package com.maukaim.bulo.definitions.registry.serialization.mixins.functional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.client.models.ParameterDefinitionDto;
import com.maukaim.bulo.io.definitions.client.models.StageInputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.models.StageOutputDefinitionDto;
import com.maukaim.bulo.io.definitions.client.models.functional.FsStageDto;
import com.maukaim.bulo.io.definitions.client.models.functional.OutputProviderDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionalStageDefinitionDtoMixIn {
    @JsonCreator
    public FunctionalStageDefinitionDtoMixIn(@JsonProperty("definitionId") String definitionId,
                                             @JsonProperty("inputsByName") Map<String, StageInputDefinitionDto> inputsByName,
                                             @JsonProperty("outputsByName") Map<String, StageOutputDefinitionDto> outputsByName,
                                             @JsonProperty("parameters") List<ParameterDefinitionDto> parameters,
                                             @JsonProperty("functionalSubStages") Set<FsStageDto> functionalSubStages,
                                             @JsonProperty("outputProviders")Set<OutputProviderDto> outputProviders) {
    }
}
