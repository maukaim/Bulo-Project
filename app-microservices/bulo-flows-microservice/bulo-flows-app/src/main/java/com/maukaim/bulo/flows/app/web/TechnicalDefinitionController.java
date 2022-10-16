package com.maukaim.bulo.flows.app.web;

import com.maukaim.bulo.flows.io.TechnicalStageDefinitionConsumer;
import com.maukaim.bulo.flows.io.events.TechnicalStageDefinitionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flows/definitions")
public class TechnicalDefinitionController {
    private final TechnicalStageDefinitionConsumer technicalStageDefinitionConsumer;

    @Autowired
    public TechnicalDefinitionController(TechnicalStageDefinitionConsumer technicalStageDefinitionConsumer) {
        this.technicalStageDefinitionConsumer = technicalStageDefinitionConsumer;
    }

    @PostMapping(value = "/onUpdate")
    public void onCreate(@RequestBody TechnicalStageDefinitionEvent event) {
        this.technicalStageDefinitionConsumer.consume(event);
    }
}
