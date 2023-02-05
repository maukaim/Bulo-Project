package com.maukaim.bulo.executors.app.web;

import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/executors/results")
public class StageRunResultController {
    private final StageRunResultStore stageRunResultStore;

    @Autowired
    public StageRunResultController(StageRunResultStore stageRunResultStore) {
        this.stageRunResultStore = stageRunResultStore;
    }

    @GetMapping(value = "/byId")
    public ResponseEntity<StageRunResult> getById(@RequestParam("stageRunId") String stageRunId) {
        StageRunResult result = this.stageRunResultStore.getById(stageRunId);
        return ResponseEntity.ok(result);
    }
}
