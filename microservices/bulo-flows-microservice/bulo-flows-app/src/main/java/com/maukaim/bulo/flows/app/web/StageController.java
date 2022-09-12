package com.maukaim.bulo.flows.app.web;

import com.maukaim.bulo.flows.io.StageUpdateEventConsumer;
import com.maukaim.bulo.flows.io.events.FlowEvent;
import com.maukaim.bulo.flows.io.events.PutFlowInstruction;
import com.maukaim.bulo.flows.io.events.RemoveFlowInstruction;
import com.maukaim.bulo.flows.io.events.StageUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flows/stages")
public class StageController {
    private final StageUpdateEventConsumer stageUpdateEventConsumer;

    @Autowired
    public StageController(StageUpdateEventConsumer stageUpdateEventConsumer) {
        this.stageUpdateEventConsumer = stageUpdateEventConsumer;
    }

    @PostMapping(value = "/createOrUpdate")
    public void onCreate(@RequestBody StageUpdateEvent event) {
        this.stageUpdateEventConsumer.onStageUpdate(event);
    }
}
