package com.maukaim.bulo.ms.shared.system.endpoints.controllers;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.ms.shared.system.endpoints.ForServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@SystemEndpointExpectedIn(ServiceName.BULO_MS_ORCHESTRATOR_SERVICE)
@RequestMapping("api/v1/system/flow-runs")
public interface IFlowRunUpdateServiceEndpoint<T> {
    @ForServiceEventType(MicroServiceEventType.FLOW_RUN_UPDATE)
    @PostMapping(value = "/update")
    void consume(@RequestBody T flowRunEvent);
}
