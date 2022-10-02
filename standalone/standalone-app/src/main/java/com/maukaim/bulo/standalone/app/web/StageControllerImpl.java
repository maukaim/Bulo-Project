package com.maukaim.bulo.standalone.app.web;

import com.maukaim.bulo.app.commons.endpoints.StageController;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.data.lifecycle.StageDtoAdapter;
import com.maukaim.bulo.stages.io.CreateStageEventConsumer;
import com.maukaim.bulo.stages.io.DeleteStageEventConsumer;
import com.maukaim.bulo.stages.io.events.CreateStageInstruction;
import com.maukaim.bulo.stages.io.events.DeleteStageInstruction;
import com.maukaim.bulo.stages.io.models.stages.StageDto;
import com.maukaim.bulo.stages.models.stage.Stage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class StageControllerImpl implements StageController {
    private final StageService service;
    private final DeleteStageEventConsumer deleteStageEventConsumer;
    private final CreateStageEventConsumer createStageEventConsumer;
    private final StageDtoAdapter stageDtoAdapter;

    public StageControllerImpl(StageService service,
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