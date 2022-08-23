package com.maukaim.bulo.runs.orchestrators.data.models;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Set;

public class Flow {
    private final String flowId;
    private final String admin;
    private final String team;
    private final ExecutionGraph<FlowStageId> executionGraph;
    private final boolean allowParallelRun;

    public Flow(String flowId, String admin, String team, ExecutionGraph<FlowStageId> executionGraph, boolean allowParallelRun) {
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

    public ExecutionGraph<FlowStageId> getExecutionGraph() {
        return executionGraph;
    }

    public boolean areRootStages(Set<FlowStageId> stageIds){
        return this.executionGraph.getRootsIds().containsAll(stageIds);
    }

    public boolean isParallelRunAllowed() {
        return allowParallelRun;
    }
}
