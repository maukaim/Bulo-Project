package com.maukaim.bulo.standalone.app.web;

import com.maukaim.bulo.app.commons.endpoints.controller.StageClientEndpoint;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.data.lifecycle.StageDtoAdapter;
import com.maukaim.bulo.io.stages.CreateStageEventConsumer;
import com.maukaim.bulo.io.stages.DeleteStageEventConsumer;
import com.maukaim.bulo.io.stages.events.CreateStageInstruction;
import com.maukaim.bulo.io.stages.events.DeleteStageInstruction;
import com.maukaim.bulo.io.stages.models.stages.StageDto;
import com.maukaim.bulo.stages.models.stage.Stage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StageController implements StageClientEndpoint {
    private final StageService service;
    private final DeleteStageEventConsumer deleteStageEventConsumer;
    private final CreateStageEventConsumer createStageEventConsumer;
    private final StageDtoAdapter stageDtoAdapter;

    public StageController(StageService service,
                           DeleteStageEventConsumer deleteStageEventConsumer,
                           CreateStageEventConsumer createStageEventConsumer,
                           StageDtoAdapter stageDtoAdapter) {
        this.service = service;
        this.deleteStageEventConsumer = deleteStageEventConsumer;
        this.createStageEventConsumer = createStageEventConsumer;
        this.stageDtoAdapter = stageDtoAdapter;
    }

    public ResponseEntity<?> create(@RequestBody CreateStageInstruction instruction) {
        String stageId = this.createStageEventConsumer.consume(instruction);
        return ResponseEntity.ok(stageId);
    }

    @Override
    public ResponseEntity<?> creates(List<CreateStageInstruction> instructions) {
        return ResponseEntity.ok(instructions.stream()
                .map(this.createStageEventConsumer::consume)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<?> remove(@RequestBody DeleteStageInstruction instruction) {
        Stage stageToBeRemoved = this.service.getById(instruction.getStageId());
        this.deleteStageEventConsumer.consume(instruction);
        return ResponseEntity.ok(stageToBeRemoved);
    }

    public ResponseEntity<Collection<StageDto>> getAll() {
        List<Stage> stages = this.service.getAll();
        return ResponseEntity.ok(stages == null ? List.of() : stages.stream()
                .map(this.stageDtoAdapter::adapte)
                .toList());
    }
}