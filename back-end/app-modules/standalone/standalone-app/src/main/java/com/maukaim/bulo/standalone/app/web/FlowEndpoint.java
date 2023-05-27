package com.maukaim.bulo.standalone.app.web;

import com.maukaim.bulo.app.endpoints.client.controller.FlowClientEndpoint;
import com.maukaim.bulo.io.flows.client.CreateFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.client.GetAllFlowsInstructionConsumer;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.client.CreateFlowInstruction;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstruction;
import com.maukaim.bulo.io.flows.client.model.FlowDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlowEndpoint implements FlowClientEndpoint {
    private final CreateFlowInstructionConsumer createFlowInstructionConsumer;
    private final RemoveFlowInstructionConsumer removeFlowInstructionConsumer;
    private final GetAllFlowsInstructionConsumer getAllFlowsInstructionConsumer;

    public FlowEndpoint(CreateFlowInstructionConsumer createFlowInstructionConsumer,
                        RemoveFlowInstructionConsumer removeFlowInstructionConsumer,
                        GetAllFlowsInstructionConsumer getAllFlowsInstructionConsumer){
        this.createFlowInstructionConsumer = createFlowInstructionConsumer;
        this.removeFlowInstructionConsumer = removeFlowInstructionConsumer;
        this.getAllFlowsInstructionConsumer = getAllFlowsInstructionConsumer;
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

    @Override
    public ResponseEntity<List<FlowDto>> getAll() {
        return ResponseEntity.ok(getAllFlowsInstructionConsumer.getAll());
    }
}
