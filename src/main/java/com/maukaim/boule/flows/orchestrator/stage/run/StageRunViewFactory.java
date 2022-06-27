package com.maukaim.boule.flows.orchestrator.stage.run;

public class StageRunViewFactory {

    public static StageRunView requested(String flowRunId, String stageId) {
        return new StageRunView(null, stageId, flowRunId, StageRunStatus.REQUESTED, null);
    }

    public static StageRunView acknowledged(StageRunView previousView, String executorId) {
        return new StageRunView(null, previousView.getStageId(), previousView.getFlowRunId(), StageRunStatus.ACKNOWLEDGED.resolveComparedTo(previousView.getStageRunStatus()), executorId);
    }

    public static StageRunView launched(StageRunView previousView, String executorId, String stageRunId){
        return new StageRunView(stageRunId, previousView.getStageId(), previousView.getFlowRunId(), StageRunStatus.RUNNING.resolveComparedTo(previousView.getStageRunStatus()), executorId);
    }

    public static StageRunView failed(StageRunView previousView){
        return new StageRunView(previousView.getStageRunId(), previousView.getStageId(), previousView.getFlowRunId(), StageRunStatus.FAILED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId());
    }

    public static StageRunView cancelled(StageRunView previousView){
        return new StageRunView(previousView.getStageRunId(), previousView.getStageId(), previousView.getFlowRunId(), StageRunStatus.CANCELLED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId());
    }

    public static StageRunView success(StageRunView previousView){
        return new StageRunView(previousView.getStageRunId(), previousView.getStageId(), previousView.getFlowRunId(), StageRunStatus.SUCCESS.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId());
    }
}
