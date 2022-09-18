package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.executors.io.NeedStageRunCancelEventConsumer;
import com.maukaim.bulo.executors.io.in.NeedStageRunCancelEvent;

public class NeedStageRunCancelEventConsumerImpl implements NeedStageRunCancelEventConsumer {
    private final StageRunEventProcessor stageRunEventProcessor;

    public NeedStageRunCancelEventConsumerImpl(StageRunEventProcessor stageRunEventProcessor) {
        this.stageRunEventProcessor = stageRunEventProcessor;
    }

    @Override
    public void consume(NeedStageRunCancelEvent event) {
        System.out.println("Consume event: " + event);
        this.stageRunEventProcessor.onCancelRequest(event.getStageRunId());
    }
}
