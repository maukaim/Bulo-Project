package com.maukaim.bulo.definitions.registry.app.web;


import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.stage.Stage;
import com.maukaim.bulo.definitions.io.StageUpdateEventConsumer;
import com.maukaim.bulo.definitions.io.events.StageUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/v1/stages")
public class StagesController {
    private final StageUpdateEventConsumer stageUpdateEventConsumer;
    private final StageStore stageStore;

    @Autowired
    public StagesController(StageUpdateEventConsumer stageUpdateEventConsumer,
                            StageStore stageStore) {
        this.stageUpdateEventConsumer = stageUpdateEventConsumer;
        this.stageStore = stageStore;
    }

    @PostMapping(value = "/onUpdateEvent")
    public ResponseEntity<?> onUpdateEvent(@RequestBody StageUpdateEvent event) {
        this.stageUpdateEventConsumer.onStageUpdate(event);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/onUpdateEvents")
    public ResponseEntity<?> onUpdateEvents(@RequestBody List<StageUpdateEvent> events) {
        for (StageUpdateEvent event : events) {
            this.stageUpdateEventConsumer.onStageUpdate(event);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<Stage>> getAll() {
        return ResponseEntity.ok(this.stageStore.getAll());
    }
}
