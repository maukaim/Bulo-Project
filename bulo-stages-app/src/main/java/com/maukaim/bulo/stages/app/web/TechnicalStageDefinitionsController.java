package com.maukaim.bulo.stages.app.web;


import com.maukaim.bulo.io.DeleteStageEvent;
import com.maukaim.bulo.io.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;
import com.maukaim.bulo.stages.models.stage.TechnicalStage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/v1/definitions")
public class TechnicalStageDefinitionsController {
    private final TechnicalStageDefinitionService service;

    public TechnicalStageDefinitionsController(TechnicalStageDefinitionService service) {
        this.service = service;
    }

    @PostMapping(value = "/onEvent")
    public ResponseEntity<TechnicalStageDefinition> onEvent(@RequestBody TechnicalStageDefinitionEvent event) {
        TechnicalStageDefinition definition = switch (event.getEventType()){
            case UPDATE -> this.service.put(event.getTechnicalStageDefinition());
            case DELETE -> this.service.remove(event.getTechnicalStageDefinition().getId());
        };
        return ResponseEntity.ok(definition);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<TechnicalStageDefinition>> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }
}
