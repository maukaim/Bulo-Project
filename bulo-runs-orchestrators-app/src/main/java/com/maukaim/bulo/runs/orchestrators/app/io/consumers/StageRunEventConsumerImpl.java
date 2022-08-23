package com.maukaim.bulo.runs.orchestrators.app.io.consumers;

import com.maukaim.bulo.commons.io.IStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.core.StageRunEventService;
import com.maukaim.bulo.runs.orchestrators.io.StageRunEventConsumer;

public class StageRunEventConsumerImpl implements StageRunEventConsumer {
    private final StageRunEventService stageRunEventService;

    public StageRunEventConsumerImpl(StageRunEventService stageRunEventService) {
        this.stageRunEventService = stageRunEventService;
    }

    @Override
    public void onStageRunEvent(IStageRunEvent stageRunEvent) {
        this.stageRunEventService.process(stageRunEvent);
    }
}
