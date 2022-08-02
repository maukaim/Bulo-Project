package com.maukaim.bulo.definitions.registry.app.web;

import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.definitions.registry.io.TechnicalStageDefinitionConsumer;
import com.maukaim.bulo.definitions.registry.io.TechnicalStageDefinitionDeclarationEvent;
import com.maukaim.bulo.definitions.registry.io.model.TechnicalStageDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/definitions")
public class DefinitionsController implements TechnicalStageDefinitionConsumer<ResponseEntity> {
    private final TechnicalStageDefinitionService service;

    public DefinitionsController(TechnicalStageDefinitionService service) {
        this.service = service;
    }

    @Override
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody TechnicalStageDefinitionDeclarationEvent event) {
        this.service.add(event.getStageExecutorId(), event.getTechnicalStageDefinition());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<TechnicalStageDefinition>> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }
}
