package com.maukaim.bulo.runs.orchestrators.app.io.consumers;

import com.maukaim.bulo.io.definitions.client.dtos.functional.FunctionalStageDefinitionDto;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.DefinitionUpdateEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.StageDefinitionAdapter;

public class DefinitionUpdateEventConsumerImpl implements DefinitionUpdateEventConsumer {
    private final StageDefinitionAdapter stageDefinitionAdapter;
    private final FunctionalStageDefinitionService functionalStageDefinitionService;

    public DefinitionUpdateEventConsumerImpl(StageDefinitionAdapter stageDefinitionAdapter,
                                             FunctionalStageDefinitionService functionalStageDefinitionService) {
        this.stageDefinitionAdapter = stageDefinitionAdapter;
        this.functionalStageDefinitionService = functionalStageDefinitionService;
    }

    @Override
    public void onDefinitionEvent(StageDefinitionEvent event) {
        System.out.println("Consuming event: " + event);
        switch (event.getStageDefinition().getStageDefinitionType()) {
            case FUNCTIONAL -> onFunctionalDefinition(event);
            case TECHNICAL -> System.out.println("Ignored event when Technical stage related.");
        }
    }

    private void onFunctionalDefinition(StageDefinitionEvent event) {
        FunctionalStageDefinitionDto stageDefinition = (FunctionalStageDefinitionDto) event.getStageDefinition();
        switch (event.getEventType()) {
            case UPDATE -> {
                FunctionalStageDefinition definition = this.stageDefinitionAdapter.adapte(stageDefinition);
                this.functionalStageDefinitionService.put(definition);
            }
            case DELETE -> this.functionalStageDefinitionService.remove(stageDefinition.getDefinitionId());
        }
    }
}
