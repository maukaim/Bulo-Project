package com.maukaim.bulo.app.endpoints.client.controller;

import com.maukaim.bulo.app.endpoints.client.ClientEventType;
import com.maukaim.bulo.app.endpoints.client.ForClientEventType;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import com.maukaim.bulo.io.flows.client.CreateFlowInstruction;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstruction;
import com.maukaim.bulo.io.flows.client.model.FlowDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SystemEndpointExpectedIn({ServiceName.BULO_MS_FLOWS_SERVICE, ServiceName.STANDALONE})
@RequestMapping(value = "api/v1/flows")
public interface FlowClientEndpoint {
    @ForClientEventType(ClientEventType.FLOW_CREATE_INSTRUCTION)
    @PostMapping(value = "/createOrUpdate")
    ResponseEntity<?> onCreate(@RequestBody CreateFlowInstruction instruction);

    @ForClientEventType(ClientEventType.FLOW_REMOVE_INSTRUCTION)
    @PostMapping(value = "/remove")
    void onRemove(@RequestBody RemoveFlowInstruction instruction);

    @ForClientEventType(ClientEventType.FLOW_REMOVE_INSTRUCTION)
    @GetMapping
    ResponseEntity<List<FlowDto>> getAll();
}
