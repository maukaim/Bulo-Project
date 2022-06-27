package com.maukaim.boule.flows.orchestrator.web.view;

import com.maukaim.boule.flows.orchestrator.run.FlowRunStatus;

import java.util.Set;

public class FlowRunView {
    private String runId;
    private String flowId;
    private FlowRunStatus status;
    private Set<String> runningStages;

    public FlowRunView(String runId, String flowId, FlowRunStatus status, Set<String> runningStages) {
        this.runId = runId;
        this.flowId = flowId;
        this.status = status;
        this.runningStages = runningStages;
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

    public Set<String> getRunningStages() {
        return runningStages;
    }
}
