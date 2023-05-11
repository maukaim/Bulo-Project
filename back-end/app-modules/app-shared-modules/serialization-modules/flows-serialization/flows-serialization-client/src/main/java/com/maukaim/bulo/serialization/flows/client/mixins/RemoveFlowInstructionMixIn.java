package com.maukaim.bulo.serialization.flows.client.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class RemoveFlowInstructionMixIn {
    @JsonCreator
    public RemoveFlowInstructionMixIn(@JsonProperty("flowId") String flowId,
                                      @JsonProperty("instant") Instant instant) {
    }
}
