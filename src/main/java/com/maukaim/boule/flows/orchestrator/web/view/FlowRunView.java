package com.maukaim.boule.flows.orchestrator.web.view;

import com.maukaim.boule.flows.orchestrator.flow.run.FlowRunStatus;
import com.maukaim.boule.flows.orchestrator.flow.view.FlowStageId;
import com.maukaim.boule.flows.orchestrator.stage.run.model.StageRunView;

import java.util.List;
import java.util.Map;

public class FlowRunView {
    private String runId;
    private String flowId;
    private FlowRunStatus status;
    private Map<FlowStageId, List<StageRunView>> stageRunsByStageId;

    public FlowRunView(String runId, String flowId, FlowRunStatus status, Map<FlowStageId, List<StageRunView>> stageRunsByStageId) {
        this.runId = runId;
        this.flowId = flowId;
        this.status = status;
        this.stageRunsByStageId = stageRunsByStageId;
    }

    public String getRunId() {
        return runId;
    }

    public String getFlowId() {
        return flowId;
    }

    public FlowRunStatus getStatus() {
        return status;
    }

    public Map<FlowStageId, List<StageRunView>> getStageRunsByStageId() {
        return stageRunsByStageId;
    }
}
