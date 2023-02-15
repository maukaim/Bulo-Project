package com.maukaim.bulo.app.commons.endpoints.controller;

import com.maukaim.bulo.app.commons.endpoints.ForClientEventType;
import com.maukaim.bulo.app.shared.system.communication.api.ServiceName;
import com.maukaim.bulo.app.shared.system.communication.api.SystemEndpointExpectedIn;
import com.maukaim.bulo.stages.io.events.CreateStageInstruction;
import com.maukaim.bulo.stages.io.events.DeleteStageInstruction;
import com.maukaim.bulo.stages.io.models.stages.StageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static com.maukaim.bulo.app.commons.endpoints.ClientEventType.*;

@SystemEndpointExpectedIn({ServiceName.BULO_MS_STAGES_SERVICE, ServiceName.STANDALONE})
@RequestMapping("api/v1/stages")
public interface StageClientEndpoint {
    @ForClientEventType(STAGE_CREATE_INSTRUCTION)
    @PostMapping(value = "/create")
    ResponseEntity<?> create(@RequestBody CreateStageInstruction instruction);

    @ForClientEventType(STAGE_MULTIPLE_CREATE_INSTRUCTION)
    @PostMapping(value = "/creates")
    ResponseEntity<?> creates(@RequestBody List<CreateStageInstruction> instructions);

    @ForClientEventType(STAGE_REMOVE_INSTRUCTION)
    @DeleteMapping(value = "/remove")
    ResponseEntity<?> remove(@RequestBody DeleteStageInstruction instruction);

    @ForClientEventType(STAGE_GET_ALL)
    @GetMapping(value = "/getAll")
    ResponseEntity<Collection<StageDto>> getAll();
}