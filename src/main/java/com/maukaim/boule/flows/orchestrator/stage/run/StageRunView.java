package com.maukaim.boule.flows.orchestrator.stage.run;

public class StageRunView {
    private final String stageRunId;
    private final String stageId;
    private final String flowRunId;
    private final StageRunStatus stageRunStatus;
    private final String executorId;

    public StageRunView(String stageRunId, String stageId, String flowRunId, StageRunStatus stageRunStatus, String executorId) {
        this.stageRunId = stageRunId;
        this.stageId = stageId;
        this.flowRunId = flowRunId;
        this.stageRunStatus = stageRunStatus;
        this.executorId = executorId;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public String getStageId() {
        return stageId;
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

    public boolean isTerminated(){
        return this.getStageRunStatus() != null && this.getStageRunStatus().isTerminal();
    }

    @Override
    public String toString() {
        return "StageRunView{" +
                "stageRunId='" + stageRunId + '\'' +
                ", stageId='" + stageId + '\'' +
                ", flowRunId='" + flowRunId + '\'' +
                ", stageRunStatus=" + stageRunStatus +
                ", executorId='" + executorId + '\'' +
                '}';
    }
}
