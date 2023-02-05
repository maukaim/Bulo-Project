package com.maukaim.bulo.ms.shared.system.endpoints.controllers;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.ms.shared.system.endpoints.ForServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@SystemEndpointExpectedIn({
        ServiceName.BULO_MS_FLOWS_SERVICE,
        ServiceName.BULO_MS_ORCHESTRATOR_SERVICE})
@RequestMapping("api/v1/system/flows")
public interface IFlowUpdateServiceEndpoint<T> {
    @ForServiceEventType(ServiceEventType.FLOW_UPDATE)
    @PostMapping(value = "/update")
    void consume(@RequestBody T flowUpdateEvent);
}
