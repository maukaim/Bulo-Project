package com.maukaim.bulo.flows.app.web;

import com.maukaim.bulo.flows.io.StageDefinitionConsumer;
import com.maukaim.bulo.flows.io.events.StageDefinitionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flows/definitions")
public class DefinitionController {
    private final StageDefinitionConsumer stageDefinitionConsumer;

    @Autowired
    public DefinitionController(StageDefinitionConsumer stageDefinitionConsumer) {
        this.stageDefinitionConsumer = stageDefinitionConsumer;
    }

    @PostMapping(value = "/onUpdate")
    public void onCreate(@RequestBody StageDefinitionEvent event) {
        this.stageDefinitionConsumer.consume(event);
    }
}
