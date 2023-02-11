package com.maukaim.bulo.stages.app.web;

import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/definitions")
public class StageDefinitionsController {
    private final StageDefinitionService service;

    public StageDefinitionsController(StageDefinitionService service) {
        this.service = service;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<StageDefinition>> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }
}
