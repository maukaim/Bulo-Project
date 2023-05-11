package com.maukaim.bulo.flows.app.web;

import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.data.models.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flows/stages")
public class StageController {
    private final StageService stageService;

    @Autowired
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @PostMapping(value = "/getById")
    public ResponseEntity<Stage> getById(@RequestParam("stageId") String stageId) {
        Stage stage = this.stageService.getById(stageId);
        return ResponseEntity.ok(stage);
    }
}
