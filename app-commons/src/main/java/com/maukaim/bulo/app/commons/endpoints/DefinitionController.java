package com.maukaim.bulo.app.commons.endpoints;

import com.maukaim.bulo.commons.io.instructions.StageDefinitionCreateInstruction;
import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping("api/v1/definitions")
public interface DefinitionController {
    @PostMapping(value = "/register")
    void consume(@RequestBody StageDefinitionCreateInstruction event);

    @GetMapping(value = "/getAll")
    ResponseEntity<Collection<StageDefinitionDto>> getAll();
}
