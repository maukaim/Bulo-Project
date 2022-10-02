package com.maukaim.bulo.standalone.app.web;

import com.maukaim.bulo.app.commons.endpoints.FlowController;
import com.maukaim.bulo.flows.io.CreateFlowInstructionConsumer;
import com.maukaim.bulo.flows.io.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.flows.io.events.CreateFlowInstruction;
import com.maukaim.bulo.flows.io.events.RemoveFlowInstruction;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowControllerImpl implements FlowController {
    private final CreateFlowInstructionConsumer createFlowInstructionConsumer;
    private final RemoveFlowInstructionConsumer removeFlowInstructionConsumer;

    public FlowControllerImpl(CreateFlowInstructionConsumer createFlowInstructionConsumer,
                              RemoveFlowInstructionConsumer removeFlowInstructionConsumer) {
        this.createFlowInstructionConsumer = createFlowInstructionConsumer;
        this.removeFlowInstructionConsumer = removeFlowInstructionConsumer;
    }

    @Override
    public void onCreate(CreateFlowInstruction instruction) {
        this.createFlowInstructionConsumer.onCreateOrUpdate(instruction);

    }

    @Override
    public void onRemove(RemoveFlowInstruction instruction) {
        this.removeFlowInstructionConsumer.onRemove(instruction);
    }
}
