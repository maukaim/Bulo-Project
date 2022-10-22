package com.maukaim.bulo.standalone.app.web;

import com.maukaim.bulo.app.commons.endpoints.DefinitionController;
import com.maukaim.bulo.commons.io.instructions.StageDefinitionCreateInstruction;
import com.maukaim.bulo.commons.io.instructions.models.StageDefinitionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DefinitionControllerImpl implements DefinitionController {
    @Override
    public void consume(StageDefinitionCreateInstruction event) {

    }

    @Override
    public ResponseEntity<Collection<StageDefinitionDto>> getAll() {
        return null;
    }
}
