package com.maukaim.bulo.definitions.registry.app.web;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionCreateInstructionConsumer;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.commons.io.instructions.TechnicalStageDefinitionCreateInstruction;
import com.maukaim.bulo.definitions.io.events.TechnicalStageDefinitionEvent;
import com.maukaim.bulo.commons.io.instructions.models.TechnicalStageDefinitionDto;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/definitions")
public class DefinitionsController {
    private final TechnicalStageDefinitionService service;
    private final TechnicalStageDefinitionCreateInstructionConsumer declarationEventConsumer;
    private final TechnicalStageDefinitionEventConsumer definitionEventConsumer;
    private final TechnicalStageDefinitionDtoAdapter definitionDtoAdapter;

    @Autowired
    public DefinitionsController(TechnicalStageDefinitionService service,
                                 TechnicalStageDefinitionCreateInstructionConsumer declarationEventConsumer,
                                 TechnicalStageDefinitionEventConsumer definitionEventConsumer,
                                 TechnicalStageDefinitionDtoAdapter definitionDtoAdapter) {
        this.service = service;
        this.declarationEventConsumer = declarationEventConsumer;
        this.definitionEventConsumer = definitionEventConsumer;
        this.definitionDtoAdapter = definitionDtoAdapter;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> consume(@RequestBody TechnicalStageDefinitionCreateInstruction event) {
        this.declarationEventConsumer.consume(event);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/onUpdateEvent")
    public ResponseEntity<?> onUpdateEvent(@RequestBody TechnicalStageDefinitionEvent event) {
        this.definitionEventConsumer.consume(event);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<TechnicalStageDefinitionDto>> getAll() {
        List<TechnicalStageDefinition> definitions = this.service.getAll();
        List<TechnicalStageDefinitionDto> definitionDtos = resolve(definitions);
        return ResponseEntity.ok(definitionDtos);
    }

    private List<TechnicalStageDefinitionDto> resolve(List<TechnicalStageDefinition> definitions) {
        return definitions == null ? List.of() : definitions.stream()
                .map(this.definitionDtoAdapter::adapte)
                .collect(Collectors.toList());
    }
}
