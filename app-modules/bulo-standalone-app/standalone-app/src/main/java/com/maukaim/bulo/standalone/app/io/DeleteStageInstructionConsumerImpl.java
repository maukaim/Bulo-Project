package com.maukaim.bulo.standalone.app.io;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.io.DeleteStageEventConsumer;
import com.maukaim.bulo.stages.io.events.DeleteStageInstruction;
import com.maukaim.bulo.stages.models.stage.Stage;

public class DeleteStageInstructionConsumerImpl implements DeleteStageEventConsumer {
    private final StageService service;

    public DeleteStageInstructionConsumerImpl(StageService service) {
        this.service = service;
    }

    @Override
    public String consume(DeleteStageInstruction event) {
        System.out.println("Consume event: " + event);
        Stage removed = this.service.remove(event.getStageId());
        return removed.getStageId();
    }
}
