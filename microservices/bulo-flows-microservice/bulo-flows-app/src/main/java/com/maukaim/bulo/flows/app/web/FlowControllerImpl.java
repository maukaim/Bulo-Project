package com.maukaim.bulo.flows.app.web;

import com.maukaim.bulo.app.commons.endpoints.FlowController;
import com.maukaim.bulo.flows.io.CreateFlowInstructionConsumer;
import com.maukaim.bulo.flows.io.FlowEventConsumer;
import com.maukaim.bulo.flows.io.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.flows.io.events.CreateFlowInstruction;
import com.maukaim.bulo.flows.io.events.FlowEvent;
import com.maukaim.bulo.flows.io.events.RemoveFlowInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowControllerImpl implements FlowController {
    private final CreateFlowInstructionConsumer createFlowInstructionConsumer;
    private final RemoveFlowInstructionConsumer removeFlowInstructionConsumer;
    private final FlowEventConsumer flowEventConsumer;

    @Autowired
    public FlowControllerImpl(CreateFlowInstructionConsumer createFlowInstructionConsumer,
                              RemoveFlowInstructionConsumer removeFlowInstructionConsumer,
                              FlowEventConsumer flowEventConsumer) {
        this.createFlowInstructionConsumer = createFlowInstructionConsumer;
        this.removeFlowInstructionConsumer = removeFlowInstructionConsumer;
        this.flowEventConsumer = flowEventConsumer;
    }

    @Override
    public void onCreate(@RequestBody CreateFlowInstruction instruction) {
        this.createFlowInstructionConsumer.onCreateOrUpdate(instruction);
    }

    @Override
    public void onRemove(@RequestBody RemoveFlowInstruction instruction) {
        this.removeFlowInstructionConsumer.onRemove(instruction);
    }

    @PostMapping(value = "/flowUpdate")
    public void onFlowEvent(@RequestBody FlowEvent event) {
        this.flowEventConsumer.onFlowEvent(event);
    }
}
