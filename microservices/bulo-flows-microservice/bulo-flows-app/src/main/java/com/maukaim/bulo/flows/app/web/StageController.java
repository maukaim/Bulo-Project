package com.maukaim.bulo.flows.app.web;

import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.data.models.stage.Stage;
import com.maukaim.bulo.flows.io.StageUpdateEventConsumer;
import com.maukaim.bulo.flows.io.events.StageUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/flows/stages")
public class StageController {
    private final StageUpdateEventConsumer stageUpdateEventConsumer;
    private final StageService stageService;

    @Autowired
    public StageController(StageUpdateEventConsumer stageUpdateEventConsumer, StageService stageService) {
        this.stageUpdateEventConsumer = stageUpdateEventConsumer;
        this.stageService = stageService;
    }

    @PostMapping(value = "/createOrUpdate")
    public void onCreate(@RequestBody StageUpdateEvent event) {
        this.stageUpdateEventConsumer.onStageUpdate(event);
    }

    @PostMapping(value = "/getById")
    public ResponseEntity<Stage> getById(@RequestParam("globalStageId") String stageId) {
        Stage stage = this.stageService.getById(stageId);
        return ResponseEntity.ok(stage);
    }
}
