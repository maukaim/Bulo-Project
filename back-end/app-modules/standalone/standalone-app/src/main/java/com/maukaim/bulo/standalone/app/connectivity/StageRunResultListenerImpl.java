package com.maukaim.bulo.standalone.app.connectivity;

import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FunctionalStageRunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunContext;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.FlowRunContext;
import com.maukaim.bulo.standalone.data.lifecycle.StageRunResultListener;

import java.time.Instant;
import java.util.function.Consumer;

public class StageRunResultListenerImpl implements StageRunResultListener {
    private final AcknowledgeStageRunEventProcessor acknowledgeStageEventProcessor;
    private final RunCancelledStageRunEventProcessor runCancelledStageEventProcessor;
    private final RunSuccessfulStageRunEventProcessor runSuccessfulStageEventProcessor;
    private final RunFailedStageRunEventProcessor runFailedStageEventProcessor;
    private final StartRunStageRunEventProcessor startRunStageEventProcessor;
    private final StageRunStore stageRunStore;

    public StageRunResultListenerImpl(AcknowledgeStageRunEventProcessor acknowledgeStageEventProcessor,
                                      RunCancelledStageRunEventProcessor runCancelledStageEventProcessor,
                                      RunSuccessfulStageRunEventProcessor runSuccessfulStageEventProcessor,
                                      RunFailedStageRunEventProcessor runFailedStageEventProcessor,
                                      StartRunStageRunEventProcessor startRunStageEventProcessor,
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
        processUnderContext(stageRunId,
                flowContext ->
                        acknowledgeStageEventProcessor.process(stageRunId, executorId, flowContext),
                functionalStageContext ->
                        acknowledgeStageEventProcessor.process(stageRunId, executorId, functionalStageContext)
        );
    }

    @Override
    public void onStarted(String stageRunId, Instant now) {
        processUnderContext(stageRunId,
                flowContext ->
                        startRunStageEventProcessor.process(stageRunId, now, flowContext),
                functionalStageContext ->
                        startRunStageEventProcessor.process(stageRunId, now, functionalStageContext)
        );
    }

    @Override
    public void onCancelled(String stageRunId, Instant now) {
        processUnderContext(stageRunId,
                flowContext ->
                        runCancelledStageEventProcessor.process(stageRunId, now, flowContext),
                functionalStageContext ->
                        runCancelledStageEventProcessor.process(stageRunId, now, functionalStageContext)
        );
    }

    @Override
    public void onFailed(String stageRunId, Instant now) {
        processUnderContext(stageRunId,
                flowContext ->
                        runFailedStageEventProcessor.process(stageRunId, now, flowContext),
                functionalStageContext ->
                        runFailedStageEventProcessor.process(stageRunId, now, functionalStageContext)
        );
    }

    @Override
    public void onSuccessful(String stageRunId, Instant now) {
        processUnderContext(stageRunId,
                flowContext ->
                        runSuccessfulStageEventProcessor.process(stageRunId, now, flowContext),
                functionalStageContext ->
                        runSuccessfulStageEventProcessor.process(stageRunId, now, functionalStageContext)
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
