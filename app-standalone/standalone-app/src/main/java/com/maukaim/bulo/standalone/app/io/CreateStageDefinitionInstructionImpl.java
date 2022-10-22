package com.maukaim.bulo.standalone.app.io;


import com.maukaim.bulo.commons.io.instructions.StageDefinitionCreateInstruction;
import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionAdapter;
import com.maukaim.bulo.definitions.io.StageDefinitionCreateInstructionConsumer;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;

public class CreateStageDefinitionInstructionImpl implements StageDefinitionCreateInstructionConsumer {
    private final StageDefinitionService stageDefinitionService;
    private final StageDefinitionAdapter definitionAdapter;

    public CreateStageDefinitionInstructionImpl(StageDefinitionService stageDefinitionService, StageDefinitionAdapter definitionAdapter) {
        this.stageDefinitionService = stageDefinitionService;
        this.definitionAdapter = definitionAdapter;
    }

    @Override
    public void consume(StageDefinitionCreateInstruction event) {
        System.out.println("Consume event: " + event);
        StageDefinition definition = this.definitionAdapter.adapte(event.getStageDefinition());
        switch (definition.getStageDefinitionType()) {
            case FUNCTIONAL -> this.stageDefinitionService.register((FunctionalStageDefinition) definition);
            case TECHNICAL -> this.stageDefinitionService.register(event.getStageExecutorId(), (TechnicalStageDefinition) definition);
        }
    }
}
