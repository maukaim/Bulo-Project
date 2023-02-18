package com.maukaim.bulo.definitions.registry.app.web;

import com.maukaim.bulo.app.commons.endpoints.controller.DefinitionClientEndpoint;
import com.maukaim.bulo.io.definitions.shared.instructions.CreateStageDefinitionInstruction;
import com.maukaim.bulo.io.definitions.shared.instructions.models.StageDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionDtoAdapter;
import com.maukaim.bulo.io.definitions.CreateStageDefinitionConsumer;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DefinitionController implements DefinitionClientEndpoint {
    private final StageDefinitionService service;
    private final CreateStageDefinitionConsumer declarationEventConsumer;
    private final StageDefinitionDtoAdapter definitionDtoAdapter;

    @Autowired
    public DefinitionController(StageDefinitionService service,
                                CreateStageDefinitionConsumer declarationEventConsumer,
                                StageDefinitionDtoAdapter definitionDtoAdapter) {
        this.service = service;
        this.declarationEventConsumer = declarationEventConsumer;
        this.definitionDtoAdapter = definitionDtoAdapter;
    }

    @Override
    public ResponseEntity<?> consume(@RequestBody CreateStageDefinitionInstruction event) {
        String definitionId = this.declarationEventConsumer.consume(event);
        return ResponseEntity.ok(definitionId);
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
