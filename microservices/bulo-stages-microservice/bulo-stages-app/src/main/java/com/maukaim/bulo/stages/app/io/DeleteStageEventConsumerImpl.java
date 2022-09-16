package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.io.DeleteStageEventConsumer;
import com.maukaim.bulo.stages.io.events.DeleteStageEvent;
import com.maukaim.bulo.stages.models.stage.Stage;

public class DeleteStageEventConsumerImpl implements DeleteStageEventConsumer {
    private final StageService stageService;

    public DeleteStageEventConsumerImpl(StageService stageService) {
        this.stageService = stageService;
    }

    @Override
    public String consume(DeleteStageEvent event) {
        System.out.println("Consume event: " + event);
        Stage removed = this.stageService.remove(event.getStageId());
        return removed.getStageId();
    }
}
