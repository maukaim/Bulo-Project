package com.maukaim.bulo.runs.orchestrator.stage.run.model;

import com.maukaim.bulo.runs.orchestrator.flow.view.FlowStageId;

import java.util.UUID;

public class StageRunViewFactory {

    public static StageRunView requested(String flowRunId, FlowStageId stageId) {
        return new StageRunView(UUID.randomUUID().toString(), stageId, flowRunId, StageRunStatus.REQUESTED, null);
    }

    public static StageRunView acknowledged(StageRunView previousView, String executorId) {
        return new StageRunView(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.ACKNOWLEDGED.resolveComparedTo(previousView.getStageRunStatus()), executorId);
    }

    public static StageRunView launched(StageRunView previousView){
        return new StageRunView(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.RUNNING.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId());
    }

    public static StageRunView failed(StageRunView previousView){
        return new StageRunView(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.FAILED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId());
    }

    public static StageRunView cancelled(StageRunView previousView){
        return new StageRunView(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.CANCELLED.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId());
    }

    public static StageRunView success(StageRunView previousView){
        return new StageRunView(previousView.getStageRunId(), previousView.getFlowStageId(), previousView.getFlowRunId(), StageRunStatus.SUCCESS.resolveComparedTo(previousView.getStageRunStatus()), previousView.getExecutorId());
    }
}
