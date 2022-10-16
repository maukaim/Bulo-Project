package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunStatusDto;

import java.util.Map;

public class FlowRunDtoMixIn {
    @JsonCreator
    public FlowRunDtoMixIn(@JsonProperty("flowRunId") String flowRunId,
                           @JsonProperty("flowId") String flowId,
                           @JsonProperty("executionGraph") ExecutionGraphDto executionGraph,
                           @JsonProperty("stageRunByIds") Map<String, StageRunDto> stageRunByIds,
                           @JsonProperty("flowRunStatus") FlowRunStatusDto flowRunStatus){}
}
