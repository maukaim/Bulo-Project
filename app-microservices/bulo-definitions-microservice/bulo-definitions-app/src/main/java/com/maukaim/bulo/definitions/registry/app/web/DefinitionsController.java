package com.maukaim.bulo.definitions.registry.app.web;

import com.maukaim.bulo.app.commons.endpoints.DefinitionController;
import com.maukaim.bulo.commons.io.instructions.StageDefinitionCreateInstruction;
import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.io.StageDefinitionCreateInstructionConsumer;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.definitions.io.events.StageDefinitionEvent;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DefinitionsController implements DefinitionController {
    private final StageDefinitionService service;
    private final StageDefinitionCreateInstructionConsumer declarationEventConsumer;
    private final TechnicalStageDefinitionEventConsumer definitionEventConsumer;
    private final StageDefinitionDtoAdapter definitionDtoAdapter;

    @Autowired
    public DefinitionsController(StageDefinitionService service,
                                 StageDefinitionCreateInstructionConsumer declarationEventConsumer,
                                 TechnicalStageDefinitionEventConsumer definitionEventConsumer,
                                 StageDefinitionDtoAdapter definitionDtoAdapter) {
        this.service = service;
        this.declarationEventConsumer = declarationEventConsumer;
        this.definitionEventConsumer = definitionEventConsumer;
        this.definitionDtoAdapter = definitionDtoAdapter;
    }

    @Override
    public void consume(@RequestBody StageDefinitionCreateInstruction event) {
        this.declarationEventConsumer.consume(event);
    }

    @PostMapping(value = "/onUpdateEvent")
    public ResponseEntity<?> onUpdateEvent(@RequestBody StageDefinitionEvent event) {
        this.definitionEventConsumer.consume(event);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Collection<StageDefinitionDto>> getAll() {
        List<StageDefinition> definitions = this.service.getAll();
        List<StageDefinitionDto> definitionDtos = resolve(definitions);
        return ResponseEntity.ok(definitionDtos);
    }

    private List<StageDefinitionDto> resolve(List<StageDefinition> definitions) {
        return definitions == null ? List.of() : definitions.stream()
                .map(this.definitionDtoAdapter::adapte)
                .collect(Collectors.toList());
    }
}
