package com.maukaim.bulo.flows.serialization.mixins.flow;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.flows.system.flow.FlowDto;

import java.time.Instant;

public class CreateFlowInstructionMixIn{
    @JsonCreator
    public CreateFlowInstructionMixIn(@JsonProperty("flow") FlowDto flowDto,
                                      @JsonProperty("instant")Instant instant) {
    }

}
