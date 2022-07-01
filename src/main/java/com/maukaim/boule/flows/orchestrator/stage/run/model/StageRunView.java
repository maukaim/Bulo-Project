package com.maukaim.boule.flows.orchestrator.stage.run.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maukaim.boule.flows.orchestrator.flow.view.FlowStageId;

public class StageRunView {
    private final String stageRunId;
    private final FlowStageId flowStageId;
    private final String flowRunId;
    private final StageRunStatus stageRunStatus;
    private final String executorId;
//    private final Date startTime;
//    private final Date endTime;

    public StageRunView(String stageRunId, FlowStageId flowStageId, String flowRunId, StageRunStatus stageRunStatus, String executorId) {
        this.stageRunId = stageRunId;
        this.flowStageId = flowStageId;
        this.flowRunId = flowRunId;
        this.stageRunStatus = stageRunStatus;
        this.executorId = executorId;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public FlowStageId getFlowStageId() {
        return flowStageId;
    }

    public String getFlowRunId() {
        return flowRunId;
    }

    public StageRunStatus getStageRunStatus() {
        return stageRunStatus;
    }

    public String getExecutorId() {
        return executorId;
    }

    @JsonIgnore
    public boolean isTerminated(){
        return this.getStageRunStatus() != null && this.getStageRunStatus().isTerminal();
    }

    @Override
    public String toString() {
        return "StageRunView{" +
                "stageRunId='" + stageRunId + '\'' +
                ", stageId='" + flowStageId + '\'' +
                ", flowRunId='" + flowRunId + '\'' +
                ", stageRunStatus=" + stageRunStatus +
                ", executorId='" + executorId + '\'' +
                '}';
    }
}
