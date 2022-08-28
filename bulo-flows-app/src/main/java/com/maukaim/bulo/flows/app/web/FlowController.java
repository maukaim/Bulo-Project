package com.maukaim.bulo.flows.app.web;

import com.maukaim.bulo.flows.io.CreateFlowInstructionConsumer;
import com.maukaim.bulo.flows.io.FlowEventConsumer;
import com.maukaim.bulo.flows.io.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.flows.io.events.PutFlowInstruction;
import com.maukaim.bulo.flows.io.events.FlowEvent;
import com.maukaim.bulo.flows.io.events.RemoveFlowInstruction;
import com.maukaim.bulo.flows.io.events.StageUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "api/v1/flows")
public class FlowController {
    private final CreateFlowInstructionConsumer createFlowInstructionConsumer;
    private final RemoveFlowInstructionConsumer removeFlowInstructionConsumer;
    private final FlowEventConsumer flowEventConsumer;

    @Autowired
    public FlowController(CreateFlowInstructionConsumer createFlowInstructionConsumer,
                          RemoveFlowInstructionConsumer removeFlowInstructionConsumer,
                          FlowEventConsumer flowEventConsumer) {
        this.createFlowInstructionConsumer = createFlowInstructionConsumer;
        this.removeFlowInstructionConsumer = removeFlowInstructionConsumer;
        this.flowEventConsumer = flowEventConsumer;
    }

    @PostMapping(value = "/createOrUpdate", consumes = {"application/json"})
    public void onCreate(@RequestBody PutFlowInstruction instruction) {
        this.createFlowInstructionConsumer.onCreateOrUpdate(instruction);
    }

    @PostMapping(value = "/remove")
    public void onRemove(@RequestBody RemoveFlowInstruction instruction) {
        this.removeFlowInstructionConsumer.onRemove(instruction);
    }

    @PostMapping(value = "/flowUpdate")
    public void onFlowEvent(@RequestBody FlowEvent event) {
        this.flowEventConsumer.onFlowEvent(event);
    }
}
