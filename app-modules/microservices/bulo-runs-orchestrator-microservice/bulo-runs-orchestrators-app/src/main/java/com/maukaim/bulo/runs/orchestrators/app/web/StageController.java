package com.maukaim.bulo.runs.orchestrators.app.web;


import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageService;
import com.maukaim.bulo.runs.orchestrators.io.events.StageUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orchestrator/stages")
public class StageController {
    private final FunctionalStageService stageService;

    @Autowired
    public StageController(FunctionalStageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping(value = "/getDefinitionId")
    public ResponseEntity<?> getDefinitionId(@RequestParam String stageId) {
        String definitionId = this.stageService.getDefinitionId(stageId);
        if (definitionId == null) {
            throw new RuntimeException("No Definition Id with Stage Id " + stageId);
        } else {
            return ResponseEntity.ok(definitionId);
        }
    }
}
