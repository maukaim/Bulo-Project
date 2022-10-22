package com.maukaim.bulo.stages.app.web;


import com.maukaim.bulo.stages.io.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.stages.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/definitions")
public class TechnicalStageDefinitionsController {
    private final TechnicalStageDefinitionService service;
    private final TechnicalStageDefinitionEventConsumer definitionEventConsumer;

    public TechnicalStageDefinitionsController(TechnicalStageDefinitionService service, TechnicalStageDefinitionEventConsumer definitionEventConsumer) {
        this.service = service;
        this.definitionEventConsumer = definitionEventConsumer;
    }

    @PostMapping(value = "/onEvent")
    public ResponseEntity<StageDefinition> onEvent(@RequestBody TechnicalStageDefinitionEvent event) {
        this.definitionEventConsumer.consume(event);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<StageDefinition>> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }
}
