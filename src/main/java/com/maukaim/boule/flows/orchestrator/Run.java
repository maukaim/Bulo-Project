package com.maukaim.boule.flows.orchestrator;

public class Run {
    private final String runId;
    private final String flowId;
    private final String currentExecutionId;
    private final RunStatus runStatus;

    public Run(String runId, String flowId, String currentExecutionId, RunStatus runStatus) {
        this.runId = runId;
        this.flowId = flowId;
        this.currentExecutionId = currentExecutionId;
        this.runStatus = runStatus;
    }

    public String getRunId() {
        return runId;
    }

    public String getFlowId() {
        return flowId;
    }

    public String getCurrentExecutionId() {
        return currentExecutionId;
    }

    public RunStatus getRunStatus() {
        return runStatus;
    }
}
