package com.maukaim.bulo.runs.orchestrators.app.io.consumers;

import com.maukaim.bulo.io.executors.shared.IStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.AcknowledgeRequestStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.RunCancelledStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.RunFailedStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.RunSuccessfulStageRunEvent;
import com.maukaim.bulo.io.runs.orchestrators.events.StartRunStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FlowRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRunContext;
import com.maukaim.bulo.io.runs.orchestrators.StageRunEventConsumer;

import java.util.function.Consumer;

public class StageRunEventConsumerImpl implements StageRunEventConsumer {
    private final AcknowledgeTechnicalStageRunEventProcessor acknowledgeStageEventProcessor;
    private final RunCancelledTechnicalStageRunEventProcessor runCancelledStageEventProcessor;
    private final RunSuccessfulTechnicalStageRunEventProcessor runSuccessfulStageEventProcessor;
    private final RunFailedTechnicalStageRunEventProcessor runFailedStageEventProcessor;
    private final StartRunTechnicalStageRunEventProcessor startRunStageEventProcessor;
    private final StageRunStore stageRunStore;


    public StageRunEventConsumerImpl(AcknowledgeTechnicalStageRunEventProcessor acknowledgeStageEventProcessor,
                                     RunCancelledTechnicalStageRunEventProcessor runCancelledStageEventProcessor,
                                     RunSuccessfulTechnicalStageRunEventProcessor runSuccessfulStageEventProcessor,
                                     RunFailedTechnicalStageRunEventProcessor runFailedStageEventProcessor,
                                     StartRunTechnicalStageRunEventProcessor startRunStageEventProcessor,
                                     StageRunStore stageRunStore) {
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
            default ->
                    throw new IllegalArgumentException("Subtype of StageEvent not supported: " + event.getClass().getSimpleName());
        }
    }

    private void onAcknowledged(AcknowledgeRequestStageRunEvent event) {
        String stageRunId = event.getStageRunId();
        processUnderContext(stageRunId,
                flowContext ->
                        acknowledgeStageEventProcessor.process(stageRunId, event.getExecutorId(), flowContext),
                functionalStageContext ->
                        acknowledgeStageEventProcessor.process(stageRunId, event.getExecutorId(), functionalStageContext)
        );
    }

    private void onStarted(StartRunStageRunEvent event) {
        String stageRunId = event.getStageRunId();
        processUnderContext(stageRunId,
                flowContext ->
                        startRunStageEventProcessor.process(stageRunId, event.getInstant(), flowContext),
                functionalStageContext ->
                        startRunStageEventProcessor.process(stageRunId, event.getInstant(), functionalStageContext)
        );
    }

    private void onCancelled(RunCancelledStageRunEvent event) {
        String stageRunId = event.getStageRunId();
        processUnderContext(stageRunId,
                flowContext ->
                        runCancelledStageEventProcessor.process(stageRunId, event.getInstant(), flowContext),
                functionalStageContext ->
                        runCancelledStageEventProcessor.process(stageRunId, event.getInstant(), functionalStageContext)
        );
    }

    private void onFailed(RunFailedStageRunEvent event) {
        String stageRunId = event.getStageRunId();
        processUnderContext(stageRunId,
                flowContext ->
                        runFailedStageEventProcessor.process(stageRunId, event.getInstant(), flowContext),
                functionalStageContext ->
                        runFailedStageEventProcessor.process(stageRunId, event.getInstant(), functionalStageContext)
        );
    }

    private void onSuccessful(RunSuccessfulStageRunEvent event) {
        String stageRunId = event.getStageRunId();
        processUnderContext(stageRunId,
                flowContext ->
                        runSuccessfulStageEventProcessor.process(stageRunId, event.getInstant(), flowContext),
                functionalStageContext ->
                        runSuccessfulStageEventProcessor.process(stageRunId, event.getInstant(), functionalStageContext)
        );
    }

    private void processUnderContext(String stageRunId, Consumer<FlowRunContext> onFlowContext, Consumer<FunctionalStageRunContext> onFunctionalStageContext) {
        RunContext<?> runContext = getContext(stageRunId);
        switch (runContext.getContextType()) {
            case FLOW_RUN -> onFlowContext.accept((FlowRunContext) runContext);
            case FUNCTIONAL_STAGE_RUN -> onFunctionalStageContext.accept((FunctionalStageRunContext) runContext);
        }
    }

    private RunContext<?> getContext(String stageRunId) {
        return this.stageRunStore.getContext(stageRunId);
    }
}
