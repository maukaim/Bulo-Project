package com.maukaim.bulo.standalone.app.io;

import com.maukaim.bulo.io.definitions.client.CreateStageDefinitionInstruction;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionAdapter;
import com.maukaim.bulo.definitions.data.lifecycle.CreateStageDefinitionConsumer;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.definitions.registry.core.report.StageDefinitionCreateReport;

public class CreateStageDefinitionConsumerImpl implements CreateStageDefinitionConsumer {
    private final StageDefinitionService stageDefinitionService;
    private final StageDefinitionAdapter definitionAdapter;

    public CreateStageDefinitionConsumerImpl(StageDefinitionService stageDefinitionService, StageDefinitionAdapter definitionAdapter) {
        this.stageDefinitionService = stageDefinitionService;
        this.definitionAdapter = definitionAdapter;
    }

    @Override
    public String consume(CreateStageDefinitionInstruction event) {
        System.out.println("Consume event: " + event);
        StageDefinition definition = this.definitionAdapter.adapte(event.getStageDefinition());
        StageDefinitionCreateReport report = switch (definition.getStageDefinitionType()) {
            case FUNCTIONAL -> this.stageDefinitionService.register((FunctionalStageDefinition) definition);
            case TECHNICAL -> this.stageDefinitionService.register(event.getStageExecutorId(),
                    (TechnicalStageDefinition) definition);
        };

        return report.getDefinitionId();
    }
}
