package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.executors.core.StageRunProcessor;
import com.maukaim.bulo.executors.data.lifecycle.NeedStageRunCancelEventConsumer;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;

public class NeedStageRunCancelEventConsumerImpl implements NeedStageRunCancelEventConsumer {
    private final StageRunProcessor stageRunProcessor;

    public NeedStageRunCancelEventConsumerImpl(StageRunProcessor stageRunProcessor) {
        this.stageRunProcessor = stageRunProcessor;
    }

    @Override
    public void consume(NeedStageRunCancellationEvent event) {
        System.out.println("Consume event: " + event);
        this.stageRunProcessor.onCancelRequest(event.getStageRunId());
    }
}
