package com.maukaim.bulo.ms.shared.system.endpoints.controllers;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.ms.shared.system.endpoints.ForServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@SystemEndpointExpectedIn({ServiceName.BULO_MS_EXECUTORS_SERVICE})
@RequestMapping("api/v1/system/stage-runs")
public interface IStageRunResultServiceEndpoint<T> {
    @ForServiceEventType(MicroServiceEventType.STAGE_RUN_RESULT)
    @PostMapping(value = "/results")
    void consume(@RequestBody T event);
}
