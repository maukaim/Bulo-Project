package com.maukaim.bulo.ms.shared.system.endpoints.controllers;

import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.ms.shared.system.endpoints.ForServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@SystemEndpointExpectedIn({
        ServiceName.BULO_MS_STAGES_SERVICE,
        ServiceName.BULO_MS_FLOWS_SERVICE,
        ServiceName.BULO_MS_EXECUTORS_SERVICE,
        ServiceName.BULO_MS_DEFINITIONS_SERVICE,
        ServiceName.BULO_MS_ORCHESTRATOR_SERVICE})
@RequestMapping("api/v1/system/stages")
public interface IStageUpdateServiceEndpoint<T> {
    @ForServiceEventType(MicroServiceEventType.STAGE_UPDATE)
    @PostMapping(value = "/update")
    void consume(@RequestBody T stageUpdateEvent);
}
