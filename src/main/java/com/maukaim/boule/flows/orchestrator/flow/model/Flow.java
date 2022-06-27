package com.maukaim.boule.flows.orchestrator.flow.model;

public class Flow {
    private final String flowId;
    private final String admin;
    private final String team;
    private final ExecutionGraph executionGraph;
    private final boolean allowParallelRun;

    public Flow(String flowId, String admin, String team, ExecutionGraph executionGraph, boolean allowParallelRun) {
        this.flowId = flowId;
        this.admin = admin;
        this.team = team;
        this.executionGraph = executionGraph;
        this.allowParallelRun = allowParallelRun;
    }

    public String getFlowId() {
        return flowId;
    }

    public String getAdmin() {
        return admin;
    }

    public String getTeam() {
        return team;
    }

    public ExecutionGraph getExecutionGraph() {
        return executionGraph;
    }

    public boolean isRootStage(String stageId){
        return this.executionGraph.getRootsIds().contains(stageId);
    }

    public boolean isParallelRunAllowed() {
        return allowParallelRun;
    }
}
