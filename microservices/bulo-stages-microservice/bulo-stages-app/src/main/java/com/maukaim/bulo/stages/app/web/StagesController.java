package com.maukaim.bulo.stages.app.web;


import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.io.CreateStageEventConsumer;
import com.maukaim.bulo.stages.io.DeleteStageEventConsumer;
import com.maukaim.bulo.stages.io.StageUpdateEventConsumer;
import com.maukaim.bulo.stages.io.events.CreateStageEvent;
import com.maukaim.bulo.stages.io.events.DeleteStageEvent;
import com.maukaim.bulo.stages.io.events.StageUpdateEvent;
import com.maukaim.bulo.stages.models.stage.Stage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/stages")
public class StagesController {
    private final StageService service;
    private final StageUpdateEventConsumer consumer;
    private final DeleteStageEventConsumer deleteStageEventConsumer;
    private final CreateStageEventConsumer createStageEventConsumer;

    public StagesController(StageService service,
                            StageUpdateEventConsumer consumer,
                            DeleteStageEventConsumer deleteStageEventConsumer,
                            CreateStageEventConsumer createStageEventConsumer) {
        this.service = service;
        this.consumer = consumer;
        this.deleteStageEventConsumer = deleteStageEventConsumer;
        this.createStageEventConsumer = createStageEventConsumer;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CreateStageEvent event) {
        String stageId = this.createStageEventConsumer.consume(event);
        return ResponseEntity.ok(stageId);
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<?> remove(@RequestBody DeleteStageEvent event) {
        Stage stageToBeRemoved = this.service.getById(event.getStageId());
        this.deleteStageEventConsumer.consume(event);
        return ResponseEntity.ok(stageToBeRemoved);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<Stage>> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @PostMapping(value = "/onUpdateEvent")
    public ResponseEntity<Stage> onExternalUpdate(@RequestBody StageUpdateEvent event) {
        this.consumer.consume(event);
        return ResponseEntity.ok(this.service.getById(event.getStageId()));
    }
}
