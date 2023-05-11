package com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun;

import com.maukaim.bulo.io.runs.orchestrators.system.models.StageRunDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.RunContextTypeDto;

import java.util.Map;

public class FlowRunDto implements OrchestrableContextDto<String> {
    private final String flowRunId;
    private final String flowId;
    private final ExecutionGraphDto executionGraph;
    private final Map<String, StageRunDto<?>> stageRunByIds;
    private final OrchestrableContextStatusDto orchestrableContextStatus;

    public FlowRunDto(String flowRunId, String flowId, ExecutionGraphDto executionGraph, Map<String, StageRunDto<?>> stageRunByIds, OrchestrableContextStatusDto flowRunStatus) {
        this.flowRunId = flowRunId;
        this.flowId = flowId;
        this.executionGraph = executionGraph;
        this.stageRunByIds = stageRunByIds;
        this.orchestrableContextStatus = flowRunStatus;
    }

    @Override
    public RunContextTypeDto getContextType() {
        return RunContextTypeDto.FLOW_RUN;
    }

    @Override
    public String getContextId() {
        return flowRunId;
    }

    public String getFlowId() {
        return flowId;
    }

    public ExecutionGraphDto getExecutionGraph() {
        return executionGraph;
    }

    public Map<String, StageRunDto<?>> getStageRunByIds() {
        return stageRunByIds;
    }

    public OrchestrableContextStatusDto getOrchestrableContextStatus() {
        return orchestrableContextStatus;
    }

    @Override
    public String toString() {
        return "FlowRunDto{" +
                "flowRunId='" + flowRunId + '\'' +
                ", flowId='" + flowId + '\'' +
                ", executionGraph=" + executionGraph +
                ", stageRunViewByIds=" + stageRunByIds +
                ", flowRunStatus=" + orchestrableContextStatus +
                '}';
    }
}
