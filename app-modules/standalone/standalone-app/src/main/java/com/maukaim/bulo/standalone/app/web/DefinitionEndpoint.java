package com.maukaim.bulo.standalone.app.web;

import com.maukaim.bulo.app.commons.endpoints.controller.DefinitionClientEndpoint;
import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;
import com.maukaim.bulo.io.definitions.client.models.StageDefinitionDto;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionDtoAdapter;
import com.maukaim.bulo.io.definitions.system.CreateStageDefinitionConsumer;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DefinitionEndpoint implements DefinitionClientEndpoint {
    private final CreateStageDefinitionConsumer consumer;
    private final StageDefinitionService definitionService;
    private final StageDefinitionDtoAdapter definitionDtoAdapter;

    public DefinitionEndpoint(CreateStageDefinitionConsumer consumer,
                              StageDefinitionService definitionService,
                              StageDefinitionDtoAdapter definitionDtoAdapter) {
        this.consumer = consumer;
        this.definitionService = definitionService;
        this.definitionDtoAdapter = definitionDtoAdapter;
    }

    @Override
    public ResponseEntity<?> consume(CreateStageDefinitionInstruction event) {
        String definitionId = this.consumer.consume(event);
        return ResponseEntity.ok(definitionId);
    }

    @Override
    public ResponseEntity<Collection<StageDefinitionDto>> getAll() {
        List<StageDefinition> stageDefinitions = this.definitionService.getAll();
        return ResponseEntity.ok(resolve(stageDefinitions));
    }

    private Collection<StageDefinitionDto> resolve(List<StageDefinition> stageDefinitions) {
        return stageDefinitions == null ? List.of() : stageDefinitions.stream()
                .map(this.definitionDtoAdapter::adapte)
                .collect(Collectors.toList());
    }
}
