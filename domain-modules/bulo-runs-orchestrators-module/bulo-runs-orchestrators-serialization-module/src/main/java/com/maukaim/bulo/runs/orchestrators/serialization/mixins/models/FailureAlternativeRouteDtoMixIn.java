package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;

public class FailureAlternativeRouteDtoMixIn {

    @JsonCreator
    public FailureAlternativeRouteDtoMixIn(@JsonProperty("from") ContextStageId from,
                                           @JsonProperty("to") ContextStageId to,
                                           @JsonProperty("maxUsage") Integer maxUsage) {
    }
}
