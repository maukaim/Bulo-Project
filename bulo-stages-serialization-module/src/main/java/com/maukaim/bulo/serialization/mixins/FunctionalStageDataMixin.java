package com.maukaim.bulo.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.stages.ParameterData;

import java.util.List;

public class FunctionalStageDataMixin {
    @JsonCreator
    public FunctionalStageDataMixin(@JsonProperty("parameters") List<ParameterData> parameters) {
    }
}
