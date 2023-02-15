package com.maukaim.bulo.definitions.registry.app.io;

import com.maukaim.bulo.io.definitions.shared.instructions.CreateStageDefinitionInstruction;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionAdapter;
import com.maukaim.bulo.definitions.io.CreateStageDefinitionConsumer;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.definitions.registry.core.report.StageDefinitionCreateReport;

public class CreateStageDefinitionConsumerImpl implements CreateStageDefinitionConsumer {
    private final StageDefinitionService stageDefinitionService;
    private final StageDefinitionAdapter stageDefinitionAdapter;

    public CreateStageDefinitionConsumerImpl(StageDefinitionService stageDefinitionService, StageDefinitionAdapter stageDefinitionAdapter) {
        this.stageDefinitionService = stageDefinitionService;
        this.stageDefinitionAdapter = stageDefinitionAdapter;
    }

    @Override
    public String consume(CreateStageDefinitionInstruction event) {
        System.out.println("Consume event: " + event);
        StageDefinition definition = this.stageDefinitionAdapter.adapte(event.getStageDefinition());
        StageDefinitionCreateReport report = switch (definition.getStageDefinitionType()) {
            case FUNCTIONAL -> this.stageDefinitionService.register((FunctionalStageDefinition) definition);
            case TECHNICAL ->
                    this.stageDefinitionService.register(event.getStageExecutorId(), (TechnicalStageDefinition) definition);
        };

        return report.getDefinitionId();
    }
}
