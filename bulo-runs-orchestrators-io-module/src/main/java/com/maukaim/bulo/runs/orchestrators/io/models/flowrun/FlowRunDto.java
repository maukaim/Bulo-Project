package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;

import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;

import java.util.Map;

public class FlowRunDto {
    private final String flowRunId;
    private final String flowId;
    private final ExecutionGraphDto executionGraph;
    private final Map<String, StageRunDto> stageRunViewByIds;
    private final FlowRunStatusDto flowRunStatus;

    public FlowRunDto(String flowRunId, String flowId, ExecutionGraphDto executionGraph, Map<String, StageRunDto> stageRunViewByIds, FlowRunStatusDto flowRunStatus) {
        this.flowRunId = flowRunId;
        this.flowId = flowId;
        this.executionGraph = executionGraph;
        this.stageRunViewByIds = stageRunViewByIds;
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
        return stageRunViewByIds;
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
                ", stageRunViewByIds=" + stageRunViewByIds +
                ", flowRunStatus=" + flowRunStatus +
                '}';
    }
}
