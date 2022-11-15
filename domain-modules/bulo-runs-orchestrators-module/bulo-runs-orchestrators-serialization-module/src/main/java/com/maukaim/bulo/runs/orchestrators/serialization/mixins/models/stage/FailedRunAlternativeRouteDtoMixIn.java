package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models.stage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.commons.models.ContextStageId;

public class FailedRunAlternativeRouteDtoMixIn {
    @JsonCreator
    public FailedRunAlternativeRouteDtoMixIn(@JsonProperty("origin") ContextStageId origin,
                                             @JsonProperty("destination") ContextStageId destination,
                                             @JsonProperty("remainingUsage") Integer remainingUsage){}
}
