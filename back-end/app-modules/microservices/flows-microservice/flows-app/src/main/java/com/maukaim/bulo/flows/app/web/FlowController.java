package com.maukaim.bulo.flows.app.web;

import com.maukaim.bulo.app.endpoints.client.controller.FlowClientEndpoint;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.ms.data.lifecycle.FlowEventConsumer;
import com.maukaim.bulo.io.flows.client.CreateFlowInstruction;
import com.maukaim.bulo.io.flows.client.CreateFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.client.GetAllFlowsInstructionConsumer;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstruction;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.client.model.FlowDto;
import com.maukaim.bulo.io.flows.system.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlowController implements FlowClientEndpoint {
    private final CreateFlowInstructionConsumer createFlowInstructionConsumer;
    private final RemoveFlowInstructionConsumer removeFlowInstructionConsumer;
    private final FlowEventConsumer flowEventConsumer;
    private final GetAllFlowsInstructionConsumer getAllFlowsInstructionConsumer;

    @Autowired
    public FlowController(CreateFlowInstructionConsumer createFlowInstructionConsumer,
                          RemoveFlowInstructionConsumer removeFlowInstructionConsumer,
                          FlowEventConsumer flowEventConsumer,
                          GetAllFlowsInstructionConsumer getAllFlowsInstructionConsumer) {
        this.createFlowInstructionConsumer = createFlowInstructionConsumer;
        this.removeFlowInstructionConsumer = removeFlowInstructionConsumer;
        this.flowEventConsumer = flowEventConsumer;
        this.getAllFlowsInstructionConsumer = getAllFlowsInstructionConsumer;
    }

    @Override
    public ResponseEntity<?> onCreate(CreateFlowInstruction instruction) {
        String flowDefinitionId = this.createFlowInstructionConsumer.onCreateOrUpdate(instruction);
        return ResponseEntity.ok(flowDefinitionId);
    }

    @Override
    public void onRemove(@RequestBody RemoveFlowInstruction instruction) {
        this.removeFlowInstructionConsumer.onRemove(instruction);
    }

    @Override
    public ResponseEntity<List<FlowDto>> getAll() {
        return ResponseEntity.ok(this.getAllFlowsInstructionConsumer.getAll());
    }

    @PostMapping(value = "/flowUpdate")
    public void onFlowEvent(@RequestBody FlowEvent event) {
        this.flowEventConsumer.consume(event);
    }
}
