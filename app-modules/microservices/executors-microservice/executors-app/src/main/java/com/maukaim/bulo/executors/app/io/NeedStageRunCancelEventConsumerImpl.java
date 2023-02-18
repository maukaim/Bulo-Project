package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.executors.data.lifecycle.NeedStageRunCancelEventConsumer;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;

public class NeedStageRunCancelEventConsumerImpl implements NeedStageRunCancelEventConsumer {
    private final StageRunEventProcessor stageRunEventProcessor;

    public NeedStageRunCancelEventConsumerImpl(StageRunEventProcessor stageRunEventProcessor) {
        this.stageRunEventProcessor = stageRunEventProcessor;
    }

    @Override
    public void consume(NeedStageRunCancellationEvent event) {
        System.out.println("Consume event: " + event);
        this.stageRunEventProcessor.onCancelRequest(event.getStageRunId());
    }
}
