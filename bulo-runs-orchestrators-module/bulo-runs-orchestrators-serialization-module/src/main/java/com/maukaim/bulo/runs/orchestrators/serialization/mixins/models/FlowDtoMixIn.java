package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowEventType;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowStageDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.OwnerKeyDto;

import java.time.Instant;
import java.util.Set;

public class FlowDtoMixIn {
    @JsonCreator
    public FlowDtoMixIn(@JsonProperty("flowId") String flowId,
                        @JsonProperty("ownerKeys") Set<OwnerKeyDto> ownerKeys,
                        @JsonProperty("flowStages") Set<FlowStageDto> flowStages,
                        @JsonProperty("allowParallelRun") boolean allowParallelRun) {
    }
}
