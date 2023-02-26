package com.maukaim.bulo.serialization.runs.orchestrators.system.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowRunStageDto;

import java.util.Set;

public class ExecutionGraphDtoMixIn {
    @JsonCreator
    public ExecutionGraphDtoMixIn(@JsonProperty("flowRunStages") Set<FlowRunStageDto> flowRunStages){}
}
