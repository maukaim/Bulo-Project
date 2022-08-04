package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maukaim.bulo.io.stages.ParameterData;

import java.util.List;

public class TechnicalStageDataMixin {
    @JsonCreator
    public TechnicalStageDataMixin(@JsonProperty("parameters") List<ParameterData> parameters,
                                   @JsonProperty("definitionId") String definitionId){}
}
