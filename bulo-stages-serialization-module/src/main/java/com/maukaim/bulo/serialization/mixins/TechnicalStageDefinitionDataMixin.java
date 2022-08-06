package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.definitions.ParameterDefinitionData;

import java.util.List;

public class TechnicalStageDefinitionDataMixin {
    @JsonCreator
    public TechnicalStageDefinitionDataMixin(@JsonProperty("id") String technicalStageDefinitionId,
                                             @JsonProperty("parameters") List<ParameterDefinitionData> parameters) {
    }
}
