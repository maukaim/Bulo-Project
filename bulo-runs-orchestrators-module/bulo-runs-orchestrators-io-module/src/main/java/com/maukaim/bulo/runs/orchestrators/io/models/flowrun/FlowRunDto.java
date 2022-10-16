package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;

import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;

import java.util.Map;

public class FlowRunDto {
    private final String flowRunId;
    private final String flowId;
    private final ExecutionGraphDto executionGraph;
    private final Map<String, StageRunDto> stageRunByIds;
    private final FlowRunStatusDto flowRunStatus;

    public FlowRunDto(String flowRunId, String flowId, ExecutionGraphDto executionGraph, Map<String, StageRunDto> stageRunByIds, FlowRunStatusDto flowRunStatus) {
        this.flowRunId = flowRunId;
        this.flowId = flowId;
        this.executionGraph = executionGraph;
        this.stageRunByIds = stageRunByIds;
        this.flowRunStatus = flowRunStatus;
    }

    public String getFlowRunId() {
        return flowRunId;
    }

    public String getFlowId() {
        return flowId;
    }

    public ExecutionGraphDto getExecutionGraph() {
        return executionGraph;
    }

    public Map<String, StageRunDto> getStageRunByIds() {
        return stageRunByIds;
    }

    public FlowRunStatusDto getFlowRunStatus() {
        return flowRunStatus;
    }

    @Override
    public String toString() {
        return "FlowRunDto{" +
                "flowRunId='" + flowRunId + '\'' +
                ", flowId='" + flowId + '\'' +
                ", executionGraph=" + executionGraph +
                ", stageRunViewByIds=" + stageRunByIds +
                ", flowRunStatus=" + flowRunStatus +
                '}';
    }
}
