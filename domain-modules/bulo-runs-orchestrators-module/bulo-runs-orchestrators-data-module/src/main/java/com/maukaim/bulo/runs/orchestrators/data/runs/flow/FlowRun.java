package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.runs.orchestrators.data.RunContextType;
import com.maukaim.bulo.runs.orchestrators.data.OrchestrableRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FlowRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.Map;

public class FlowRun extends OrchestrableRunContext<String> {
    private final String flowRunId;
    private final String flowId;
    private final ExecutionGraph executionGraph;
    private final FailureGraph failureGraph;
    private final Map<String, StageRun> stageRunViewByIds;
    private final OrchestrableContextStatus orchestrableContextStatus;

    public FlowRun(String flowRunId,
                   String flowId,
                   ExecutionGraph executionGraph,
                   FailureGraph failureGraph,
                   Map<String, StageRun> stageRunViewByIds,
                   OrchestrableContextStatus orchestrableContextStatus) {
        this.flowRunId = flowRunId;
        this.flowId = flowId;
        this.failureGraph = failureGraph;
        this.stageRunViewByIds = Map.copyOf(stageRunViewByIds);
        this.orchestrableContextStatus = orchestrableContextStatus;
        this.executionGraph = executionGraph;
    }

    @Override
    public ExecutionGraph getExecutionGraph() {
        return this.executionGraph;
    }

    public FailureGraph getFailureGraph() {
        return failureGraph;
    }

    @Override
    public RunContextType getContextType() {
        return RunContextType.FLOW_RUN;
    }

    @Override
    public String getContextId() {
        return flowRunId;
    }

    public String getFlowId() {
        return flowId;
    }

    @Override
    public Map<String, StageRun> getStageRunsById() {
        return stageRunViewByIds;
    }

    @Override
    public OrchestrableContextStatus getStatus() {
        return orchestrableContextStatus;
    }

    @Override
    public RunContext<String> toRunContext() {
        return new FlowRunContext(getContextId());
    }

    @Override
    public String toString() {
        return "FlowRun{" +
                "flowRunId='" + flowRunId + '\'' +
                ", flowId='" + flowId + '\'' +
                ", executionGraph=" + executionGraph +
                ", failureGraph=" + failureGraph +
                ", stageRunViewByIds=" + stageRunViewByIds +
                ", orchestrableContextStatus=" + orchestrableContextStatus +
                '}';
    }
}
