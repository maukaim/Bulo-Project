package com.maukaim.bulo.flows.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.flows.client.model.FlowStageDto;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyDto;

import java.util.Set;

public class FlowDtoMixIn {

    @JsonCreator
    public FlowDtoMixIn(@JsonProperty("flowId") String flowId,
                        @JsonProperty("ownerKeys") Set<OwnerKeyDto> ownerKeyDtos,
                        @JsonProperty("flowStages") Set<FlowStageDto> flowStages,
                        @JsonProperty("allowParallelRun") boolean allowParallelRun) {
    }
}
