package com.maukaim.bulo.runs.orchestrators.serialization.mixins.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.runs.orchestrators.io.models.TechnicalStageRunDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.OrchestrableContextStatusDto;

import java.util.Map;

public class FlowRunDtoMixIn {
    @JsonCreator
    public FlowRunDtoMixIn(@JsonProperty("flowRunId") String flowRunId,
                           @JsonProperty("flowId") String flowId,
                           @JsonProperty("executionGraph") ExecutionGraphDto executionGraph,
                           @JsonProperty("stageRunByIds") Map<String, TechnicalStageRunDto> stageRunByIds,
                           @JsonProperty("flowRunStatus") OrchestrableContextStatusDto flowRunStatus){}
}
