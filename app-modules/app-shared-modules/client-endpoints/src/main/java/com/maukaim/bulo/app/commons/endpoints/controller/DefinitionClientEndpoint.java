package com.maukaim.bulo.app.commons.endpoints.controller;

import com.maukaim.bulo.app.commons.endpoints.ForClientEventType;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import com.maukaim.bulo.commons.io.instructions.CreateStageDefinitionInstruction;
import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

import static com.maukaim.bulo.app.commons.endpoints.ClientEventType.DEFINITION_GET_ALL;
import static com.maukaim.bulo.app.commons.endpoints.ClientEventType.STAGE_DEFINITION_CREATE_INSTRUCTION;

@SystemEndpointExpectedIn({ServiceName.BULO_MS_DEFINITIONS_SERVICE, ServiceName.STANDALONE})
@RequestMapping("api/v1/definitions")
public interface DefinitionClientEndpoint {
    @ForClientEventType(STAGE_DEFINITION_CREATE_INSTRUCTION)
    @PostMapping(value = "/register")
    ResponseEntity<?> consume(@RequestBody CreateStageDefinitionInstruction event);

    @ForClientEventType(DEFINITION_GET_ALL)
    @GetMapping(value = "/getAll")
    ResponseEntity<Collection<StageDefinitionDto>> getAll();
}
