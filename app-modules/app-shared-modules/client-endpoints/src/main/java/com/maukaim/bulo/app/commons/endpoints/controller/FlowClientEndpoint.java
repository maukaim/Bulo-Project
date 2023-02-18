package com.maukaim.bulo.app.commons.endpoints.controller;

import com.maukaim.bulo.app.commons.endpoints.ForClientEventType;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import com.maukaim.bulo.io.flows.system.events.CreateFlowInstruction;
import com.maukaim.bulo.io.flows.system.events.RemoveFlowInstruction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.maukaim.bulo.app.commons.endpoints.ClientEventType.FLOW_CREATE_INSTRUCTION;
import static com.maukaim.bulo.app.commons.endpoints.ClientEventType.FLOW_REMOVE_INSTRUCTION;

@SystemEndpointExpectedIn({ServiceName.BULO_MS_FLOWS_SERVICE, ServiceName.STANDALONE})
@RequestMapping(value = "api/v1/flows")
public interface FlowClientEndpoint {
    @ForClientEventType(FLOW_CREATE_INSTRUCTION)
    @PostMapping(value = "/createOrUpdate")
    ResponseEntity<?> onCreate(@RequestBody CreateFlowInstruction instruction);

    @ForClientEventType(FLOW_REMOVE_INSTRUCTION)
    @PostMapping(value = "/remove")
    void onRemove(@RequestBody RemoveFlowInstruction instruction);
}
