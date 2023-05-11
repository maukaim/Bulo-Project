package com.maukaim.bulo.app.endpoints.client.controller;

import com.maukaim.bulo.app.endpoints.client.ForClientEventType;
import com.maukaim.bulo.app.endpoints.client.ClientEventType;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;
import com.maukaim.bulo.io.definitions.client.dtos.StageDefinitionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@SystemEndpointExpectedIn({ServiceName.BULO_MS_DEFINITIONS_SERVICE, ServiceName.STANDALONE})
@RequestMapping("api/v1/definitions")
public interface DefinitionClientEndpoint {
    @ForClientEventType(ClientEventType.STAGE_DEFINITION_CREATE_INSTRUCTION)
    @PostMapping(value = "/register")
    ResponseEntity<?> consume(@RequestBody CreateStageDefinitionInstruction event);

    @ForClientEventType(ClientEventType.DEFINITION_GET_ALL)
    @GetMapping(value = "/getAll")
    ResponseEntity<Collection<StageDefinitionDto>> getAll();
}
