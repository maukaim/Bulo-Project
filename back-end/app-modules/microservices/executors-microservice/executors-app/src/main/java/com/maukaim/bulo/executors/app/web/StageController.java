package com.maukaim.bulo.executors.app.web;

import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.stages.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/executors/stages")
public class StageController {
  private final StageStore stageStore;

    @Autowired
    public StageController( StageStore stageStore) {
        this.stageStore = stageStore;
    }

    @GetMapping(value = "/byId")
    public ResponseEntity<Stage> getById(@RequestParam("stageId") String stageId) {
        Stage stage = this.stageStore.getById(stageId);
        return ResponseEntity.ok(stage);
    }
}
