package com.maukaim.bulo.runs.orchestrators.app.web;


import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orchestrator/definitions")
public class DefinitionController {
    private final FunctionalStageDefinitionService definitionService;

    @Autowired
    public DefinitionController(FunctionalStageDefinitionService definitionService) {
        this.definitionService = definitionService;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<?> getById(@RequestParam String definitionId) {
        FunctionalStageDefinition definition = this.definitionService.getById(definitionId);
        if (definition == null) {
            throw new RuntimeException("No Definition with ID " + definitionId);
        } else {
            return ResponseEntity.ok(definition);
        }
    }
}
