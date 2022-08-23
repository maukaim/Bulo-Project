package com.maukaim.bulo.runs.orchestrators.data.lifecycle;

import com.maukaim.bulo.runs.orchestrators.data.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunExecutionEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.out.NeedStageRunCancellationEvent;
import com.maukaim.bulo.runs.orchestrators.io.out.NeedStageRunExecutionEvent;
import com.maukaim.bulo.runs.orchestrators.io.out.model.IoMapping;

import java.time.Instant;

public class StageRunConnectorImpl implements StageRunConnector {
    private final NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher;
    private final NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher;

    public StageRunConnectorImpl(NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher, NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher) {
        this.needStageRunExecutionEventPublisher = needStageRunExecutionEventPublisher;
        this.needStageRunCancellationEventPublisher = needStageRunCancellationEventPublisher;
    }


    @Override
    public boolean sendCancel(String stageRunId, String executorId) {
        return this.needStageRunCancellationEventPublisher.publish(
                new NeedStageRunCancellationEvent(stageRunId, executorId, Instant.now())
        );
    }

    @Override
    public boolean sendRun(String globalStageId, String stageRunId) {
        return this.needStageRunExecutionEventPublisher.publish(
                new NeedStageRunExecutionEvent(globalStageId, stageRunId, new IoMapping(), Instant.now())
        );
    }
}
