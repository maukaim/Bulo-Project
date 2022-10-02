package com.maukaim.bulo.executors.app.web;

import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.stages.Stage;
import com.maukaim.bulo.executors.io.StageUpdateEventConsumer;
import com.maukaim.bulo.executors.io.in.StageUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/executors/stages")
public class StageController {
  private final StageUpdateEventConsumer stageUpdateEventConsumer;
  private final StageStore stageStore;

    @Autowired
    public StageController(StageUpdateEventConsumer stageUpdateEventConsumer, StageStore stageStore) {
        this.stageUpdateEventConsumer = stageUpdateEventConsumer;
        this.stageStore = stageStore;
    }

    @PostMapping(value = "/onEvent")
    public ResponseEntity<Stage> onStageUpdate(@RequestBody StageUpdateEvent event) {
        this.stageUpdateEventConsumer.onStageUpdateEvent(event);
        Stage stage = this.stageStore.getById(event.getStageId());
        return ResponseEntity.ok(stage);
    }

    @GetMapping(value = "/byId")
    public ResponseEntity<Stage> getById(@RequestParam("stageId") String stageId) {
        Stage stage = this.stageStore.getById(stageId);
        return ResponseEntity.ok(stage);
    }
}
