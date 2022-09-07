package com.maukaim.bulo.executors.serialization.mixins.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.io.DefinitionEventType;
import com.maukaim.bulo.executors.io.out.model.ParameterDefinitionDto;
import com.maukaim.bulo.executors.io.out.model.StageDefinitionDto;
import com.maukaim.bulo.executors.io.out.model.StageInputDefinitionDto;
import com.maukaim.bulo.executors.io.out.model.StageOutputDefinitionDto;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class StageDefinitionDtoMixIn {
    @JsonCreator
    public StageDefinitionDtoMixIn(
            @JsonProperty("technicalStageDefinition") String technicalStageDefinitionId,
            @JsonProperty("inputsByName") Map<String, StageInputDefinitionDto> inputsByName,
            @JsonProperty("outputsByName") Map<String, StageOutputDefinitionDto> outputsByName,
            @JsonProperty("parameters") List<ParameterDefinitionDto> parameters) {
    }
}
