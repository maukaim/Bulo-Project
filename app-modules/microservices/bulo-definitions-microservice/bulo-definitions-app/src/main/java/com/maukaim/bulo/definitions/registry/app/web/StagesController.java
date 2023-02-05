package com.maukaim.bulo.definitions.registry.app.web;


import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.stage.Stage;
import com.maukaim.bulo.definitions.io.events.StageUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/stages")
public class StagesController {
    private final StageStore stageStore;

    @Autowired
    public StagesController(StageStore stageStore) {
        this.stageStore = stageStore;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<Stage>> getAll() {
        return ResponseEntity.ok(this.stageStore.getAll());
    }
}
