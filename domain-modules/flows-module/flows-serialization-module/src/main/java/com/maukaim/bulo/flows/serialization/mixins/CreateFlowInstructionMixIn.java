package com.maukaim.bulo.flows.serialization.mixins;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.flows.client.model.FlowDto;

import java.time.Instant;

public class CreateFlowInstructionMixIn{
    @JsonCreator
    public CreateFlowInstructionMixIn(@JsonProperty("flow") FlowDto flowDto,
                                      @JsonProperty("instant")Instant instant) {
    }

}
