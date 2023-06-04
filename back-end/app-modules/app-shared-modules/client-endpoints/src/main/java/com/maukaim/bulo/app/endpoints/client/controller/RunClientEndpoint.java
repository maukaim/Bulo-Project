package com.maukaim.bulo.app.endpoints.client.controller;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SystemEndpointExpectedIn({ServiceName.BULO_MS_ORCHESTRATOR_SERVICE, ServiceName.STANDALONE})
@RequestMapping(value = "api/v1/runs")
public interface RunClientEndpoint {
    @GetMapping(value = "/getByFlowId")
    ResponseEntity<?> getByFlowId(@RequestParam String flowId);
}
