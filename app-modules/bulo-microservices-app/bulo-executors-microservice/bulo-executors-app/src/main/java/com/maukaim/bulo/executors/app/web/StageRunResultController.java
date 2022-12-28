package com.maukaim.bulo.executors.app.web;

import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.io.StageRunResultEventConsumer;
import com.maukaim.bulo.executors.io.out.StageRunResultEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/executors/results")
public class StageRunResultController {
  private final StageRunResultEventConsumer stageRunResultEventConsumer;
  private final StageRunResultStore stageRunResultStore;

    @Autowired
    public StageRunResultController(StageRunResultEventConsumer stageRunResultEventConsumer, StageRunResultStore stageRunResultStore) {
        this.stageRunResultEventConsumer = stageRunResultEventConsumer;
        this.stageRunResultStore = stageRunResultStore;
    }

    @PostMapping(value = "/onResultEvent")
    public ResponseEntity<?> onResultEvent(@RequestBody StageRunResultEvent event) {
        this.stageRunResultEventConsumer.onStageRunResultEvent(event);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/byId")
    public ResponseEntity<StageRunResult> getById(@RequestParam("stageRunId") String stageRunId) {
        StageRunResult result = this.stageRunResultStore.getById(stageRunId);
        return ResponseEntity.ok(result);
    }
}
