package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flow.FlowStageDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flow.OwnerKeyDto;

import java.util.Set;

public class FlowDtoMixIn {
    @JsonCreator
    public FlowDtoMixIn(@JsonProperty("flowId") String flowId,
                        @JsonProperty("ownerKeys") Set<OwnerKeyDto> ownerKeys,
                        @JsonProperty("flowStages") Set<FlowStageDto> flowStages,
                        @JsonProperty("allowParallelRun") boolean allowParallelRun) {
    }
}
