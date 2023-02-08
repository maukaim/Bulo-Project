package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.executors.io.NeedStageRunCancelEventConsumer;
import com.maukaim.bulo.executors.io.in.CancelRunInstruction;

public class NeedStageRunCancelEventConsumerImpl implements NeedStageRunCancelEventConsumer {
    private final StageRunEventProcessor stageRunEventProcessor;

    public NeedStageRunCancelEventConsumerImpl(StageRunEventProcessor stageRunEventProcessor) {
        this.stageRunEventProcessor = stageRunEventProcessor;
    }

    @Override
    public void consume(CancelRunInstruction event) {
        System.out.println("Consume event: " + event);
        this.stageRunEventProcessor.onCancelRequest(event.getStageRunId());
    }
}