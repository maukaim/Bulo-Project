package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.io.stages.client.DeleteStageEventConsumer;
import com.maukaim.bulo.io.stages.client.DeleteStageInstruction;
import com.maukaim.bulo.stages.models.stage.Stage;

public class DeleteStageEventConsumerImpl implements DeleteStageEventConsumer {
    private final StageService stageService;

    public DeleteStageEventConsumerImpl(StageService stageService) {
        this.stageService = stageService;
    }

    @Override
    public String consume(DeleteStageInstruction event) {
        System.out.println("Consume event: " + event);
        Stage removed = this.stageService.remove(event.getStageId());
        return removed.getStageId();
    }
}
