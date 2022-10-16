package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.InputProviderDto;

import java.util.Set;

public class InputProviderDtoMixIn {
    @JsonCreator
    public InputProviderDtoMixIn(@JsonProperty("flowStageId") FlowStageId flowStageId,
                                 @JsonProperty("outputIds") Set<String> outputIds) {
    }
}
