package com.maukaim.bulo.app.commons.endpoints;

import com.maukaim.bulo.stages.io.events.CreateStageInstruction;
import com.maukaim.bulo.stages.io.events.DeleteStageInstruction;
import com.maukaim.bulo.stages.io.events.StageUpdateEvent;
import com.maukaim.bulo.stages.io.models.stages.StageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("api/v1/stages")
public interface StageController {
    @PostMapping(value = "/create")
    ResponseEntity<?> create(@RequestBody CreateStageInstruction instruction);

    @DeleteMapping(value = "/remove")
    ResponseEntity<?> remove(@RequestBody DeleteStageInstruction instruction);

    @GetMapping(value = "/getAll")
    ResponseEntity<Collection<StageDto>> getAll();
}