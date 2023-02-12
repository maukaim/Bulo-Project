package com.maukaim.bulo.ms.shared.system.endpoints.controllers;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.ms.shared.system.endpoints.ForServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@SystemEndpointExpectedIn({ServiceName.BULO_MS_DEFINITIONS_SERVICE})
@RequestMapping("api/v1/system/definitions/executors")
public interface IExecutorUpdateServiceEndpoint<T> {
    @ForServiceEventType(ServiceEventType.EXECUTOR_UPDATE)
    @PostMapping(value = "/update")
    void consume(@RequestBody T executorUpdateEvent);
}
