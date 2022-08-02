package com.maukaim.bulo.definitions.registry.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.definitions.registry.io.model.ParameterDefinition;
import com.maukaim.bulo.definitions.registry.io.model.StageInputDefinition;
import com.maukaim.bulo.definitions.registry.io.model.StageOutputDefinition;

import java.util.List;
import java.util.Map;

public class TechnicalStageDefinitionMixIn {
    @JsonCreator
    public TechnicalStageDefinitionMixIn(@JsonProperty("technicalStageDefinitionId") String technicalStageDefinitionId,
                                         @JsonProperty("inputsByName") Map<String, StageInputDefinition> inputsByName,
                                         @JsonProperty("outputsByName") Map<String, StageOutputDefinition> outputsByName,
                                         @JsonProperty("parameters") List<ParameterDefinition> parameters) {
    }
}
