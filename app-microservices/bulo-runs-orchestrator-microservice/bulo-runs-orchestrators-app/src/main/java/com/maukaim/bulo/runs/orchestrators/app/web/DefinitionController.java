package com.maukaim.bulo.runs.orchestrators.app.web;


import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRunStoreException;
import com.maukaim.bulo.runs.orchestrators.io.DefinitionUpdateEventConsumer;
import com.maukaim.bulo.runs.orchestrators.io.events.DefinitionUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orchestrator/definitions")
public class DefinitionController {
    private final FunctionalStageDefinitionService definitionService;
    private final DefinitionUpdateEventConsumer definitionUpdateEventConsumer;

    @Autowired
    public DefinitionController(FunctionalStageDefinitionService definitionService,
                                DefinitionUpdateEventConsumer definitionUpdateEventConsumer) {
        this.definitionService = definitionService;
        this.definitionUpdateEventConsumer = definitionUpdateEventConsumer;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<?> getById(@RequestParam String definitionId) {
        FunctionalStageDefinition definition = this.definitionService.getById(definitionId);
        if (definition == null) {
            throw new RuntimeException("No Definition with ID " + definitionId);
        } else {
            return ResponseEntity.ok(definition);
        }
    }

    @PostMapping(value = "/onUpdate")
    public ResponseEntity<?> definitionEvent(@RequestBody DefinitionUpdateEvent event) {
        this.definitionUpdateEventConsumer.onDefinitionEvent(event);
        FunctionalStageDefinition definition = this.definitionService.getById(event.getStageDefinition().getDefinitionId());
        return ResponseEntity.ok(definition);
    }
}
