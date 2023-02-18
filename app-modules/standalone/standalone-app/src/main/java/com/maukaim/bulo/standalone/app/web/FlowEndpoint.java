package com.maukaim.bulo.standalone.app.web;

import com.maukaim.bulo.app.commons.endpoints.controller.FlowClientEndpoint;
import com.maukaim.bulo.io.flows.system.CreateFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.system.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.system.events.CreateFlowInstruction;
import com.maukaim.bulo.io.flows.system.events.RemoveFlowInstruction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowEndpoint implements FlowClientEndpoint {
    private final CreateFlowInstructionConsumer createFlowInstructionConsumer;
    private final RemoveFlowInstructionConsumer removeFlowInstructionConsumer;

    public FlowEndpoint(CreateFlowInstructionConsumer createFlowInstructionConsumer,
                        RemoveFlowInstructionConsumer removeFlowInstructionConsumer) {
        this.createFlowInstructionConsumer = createFlowInstructionConsumer;
        this.removeFlowInstructionConsumer = removeFlowInstructionConsumer;
    }

    @Override
    public ResponseEntity<?> onCreate(CreateFlowInstruction instruction) {
        String flowDefinitionId = this.createFlowInstructionConsumer.onCreateOrUpdate(instruction);
        return ResponseEntity.ok(flowDefinitionId);
    }

    @Override
    public void onRemove(RemoveFlowInstruction instruction) {
        this.removeFlowInstructionConsumer.onRemove(instruction);
    }
}
