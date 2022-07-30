package com.maukaim.bulo.definitions.registry.app.web;

import com.maukaim.bulo.definitions.registry.io.TechnicalStageDefinitionDeclarationEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/definitions")
public class DefinitionsController {
    @PostMapping(value = "/echo")
    public ResponseEntity<?> register(@RequestBody TechnicalStageDefinitionDeclarationEvent event) {
        return ResponseEntity.ok().build();
    }
}
