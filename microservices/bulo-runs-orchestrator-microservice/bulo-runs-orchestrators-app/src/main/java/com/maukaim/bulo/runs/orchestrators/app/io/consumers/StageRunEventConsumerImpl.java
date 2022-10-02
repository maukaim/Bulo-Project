package com.maukaim.bulo.runs.orchestrators.app.io.consumers;

import com.maukaim.bulo.commons.io.IStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.io.StageRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.events.*;

public class StageRunEventConsumerImpl implements StageRunEventConsumer {
    private final AcknowledgeStageEventProcessor acknowledgeStageEventProcessor;
    private final RunCancelledStageEventProcessor runCancelledStageEventProcessor;
    private final RunSuccessfulStageEventProcessor runSuccessfulStageEventProcessor;
    private final RunFailedStageEventProcessor runFailedStageEventProcessor;
    private final StartRunStageEventProcessor startRunStageEventProcessor;
    private final StageRunStore stageRunStore;


    public StageRunEventConsumerImpl(AcknowledgeStageEventProcessor acknowledgeStageEventProcessor,
                                     RunCancelledStageEventProcessor runCancelledStageEventProcessor,
                                     RunSuccessfulStageEventProcessor runSuccessfulStageEventProcessor,
                                     RunFailedStageEventProcessor runFailedStageEventProcessor,
                                     StartRunStageEventProcessor startRunStageEventProcessor, StageRunStore stageRunStore) {
        this.acknowledgeStageEventProcessor = acknowledgeStageEventProcessor;
        this.runCancelledStageEventProcessor = runCancelledStageEventProcessor;
        this.runSuccessfulStageEventProcessor = runSuccessfulStageEventProcessor;
        this.runFailedStageEventProcessor = runFailedStageEventProcessor;
        this.startRunStageEventProcessor = startRunStageEventProcessor;
        this.stageRunStore = stageRunStore;
    }

    @Override
    public void onStageRunEvent(IStageRunEvent event) {
        System.out.println("Consume event: " + event);
        switch (event.getEventType()) {
            case ACKNOWLEDGE_REQUEST -> this.onAcknowledged((AcknowledgeRequestStageRunEvent) event);
            case LAUNCH_RUN -> this.onStarted((StartRunStageRunEvent) event);
            case RUN_CANCELLED -> this.onCancelled((RunCancelledStageRunEvent) event);
            case RUN_FAILED -> this.onFailed((RunFailedStageRunEvent) event);
            case RUN_SUCCESSFUL -> this.onSuccessful((RunSuccessfulStageRunEvent) event);
            default -> throw new IllegalArgumentException("Subtype of StageEvent not supported: " + event.getClass().getSimpleName());
        }
    }

    private void onAcknowledged(AcknowledgeRequestStageRunEvent event){
        String stageRunId = event.getStageRunId();
        this.acknowledgeStageEventProcessor.process(stageRunId, event.getExecutorId(), getFlowRunId(stageRunId));
    }

    private void onStarted(StartRunStageRunEvent event){
        String stageRunId = event.getStageRunId();
        this.startRunStageEventProcessor.process(stageRunId, event.getInstant(), getFlowRunId(stageRunId));
    }

    private void onCancelled(RunCancelledStageRunEvent event){
        String stageRunId = event.getStageRunId();
        this.runCancelledStageEventProcessor.process(stageRunId, event.getInstant(), getFlowRunId(stageRunId));
    }

    private void onFailed(RunFailedStageRunEvent event){
        String stageRunId = event.getStageRunId();
        this.runFailedStageEventProcessor.process(stageRunId, event.getInstant(), getFlowRunId(stageRunId));
    }

    private void onSuccessful(RunSuccessfulStageRunEvent event){
        String stageRunId = event.getStageRunId();
        this.runSuccessfulStageEventProcessor.process(stageRunId, event.getInstant(), getFlowRunId(stageRunId));
    }

    private String getFlowRunId(String stageRunId){
        return this.stageRunStore.getFlowRunId(stageRunId);
    }
}
