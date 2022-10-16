package com.maukaim.bulo.standalone.app.connectivity;

import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.standalone.data.lifecycle.StageRunResultListener;

import java.time.Instant;

public class StageRunResultListenerImpl implements StageRunResultListener {
    private final AcknowledgeStageEventProcessor acknowledgeStageEventProcessor;
    private final RunCancelledStageEventProcessor runCancelledStageEventProcessor;
    private final RunSuccessfulStageEventProcessor runSuccessfulStageEventProcessor;
    private final RunFailedStageEventProcessor runFailedStageEventProcessor;
    private final StartRunStageEventProcessor startRunStageEventProcessor;
    private final StageRunStore stageRunStore;

    public StageRunResultListenerImpl(AcknowledgeStageEventProcessor acknowledgeStageEventProcessor,
                                      RunCancelledStageEventProcessor runCancelledStageEventProcessor,
                                      RunSuccessfulStageEventProcessor runSuccessfulStageEventProcessor,
                                      RunFailedStageEventProcessor runFailedStageEventProcessor,
                                      StartRunStageEventProcessor startRunStageEventProcessor,
                                      StageRunStore stageRunStore) {
        this.acknowledgeStageEventProcessor = acknowledgeStageEventProcessor;
        this.runCancelledStageEventProcessor = runCancelledStageEventProcessor;
        this.runSuccessfulStageEventProcessor = runSuccessfulStageEventProcessor;
        this.runFailedStageEventProcessor = runFailedStageEventProcessor;
        this.startRunStageEventProcessor = startRunStageEventProcessor;
        this.stageRunStore = stageRunStore;
    }

    @Override
    public void onAcknowledged(String executorId, String stageRunId, Instant now) {
        this.acknowledgeStageEventProcessor.process(stageRunId, executorId, getFlowRunId(stageRunId));
    }

    private String getFlowRunId(String stageRunId){
        return this.stageRunStore.getFlowRunId(stageRunId);
    }

    @Override
    public void onStarted(String stageRunId, Instant now) {
        this.startRunStageEventProcessor.process(stageRunId, now, getFlowRunId(stageRunId));
    }

    @Override
    public void onCancelled(String stageRunId, Instant now) {
        this.runCancelledStageEventProcessor.process(stageRunId, now, getFlowRunId(stageRunId));
    }

    @Override
    public void onFailed(String stageRunId, Instant now) {
        this.runFailedStageEventProcessor.process(stageRunId, now, getFlowRunId(stageRunId));
    }

    @Override
    public void onSuccessful(String stageRunId, Instant now) {
        this.runSuccessfulStageEventProcessor.process(stageRunId, now, getFlowRunId(stageRunId));
    }
}
