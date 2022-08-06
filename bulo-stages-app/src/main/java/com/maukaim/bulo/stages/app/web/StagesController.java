package com.maukaim.bulo.stages.app.web;


import com.maukaim.bulo.io.CreateStageEvent;
import com.maukaim.bulo.io.DeleteStageEvent;
import com.maukaim.bulo.io.StageUpdateEvent;
import com.maukaim.bulo.io.StageUpdateEventConsumer;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.models.stage.Stage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/stages")
public class StagesController {
    private final StageService service;
    private StageUpdateEventConsumer consumer;

    public StagesController(StageService service, StageUpdateEventConsumer consumer) {
        this.service = service;
        this.consumer = consumer;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CreateStageEvent event) {
        return ResponseEntity.ok(this.service.addStage(event.getStageData()));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<?> remove(@RequestBody DeleteStageEvent event) {
        return ResponseEntity.ok(this.service.remove(event.getStageId()));
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
