package com.maukaim.bulo.runs.orchestrators.app.io.consumers;

import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.StageDefinitionAdapter;
import com.maukaim.bulo.io.runs.orchestrators.system.DefinitionUpdateEventConsumer;
import com.maukaim.bulo.io.runs.orchestrators.system.events.DefinitionUpdateEvent;

public class DefinitionUpdateEventConsumerImpl implements DefinitionUpdateEventConsumer {
    private final StageDefinitionAdapter stageDefinitionAdapter;
    private final FunctionalStageDefinitionService functionalStageDefinitionService;

    public DefinitionUpdateEventConsumerImpl(StageDefinitionAdapter stageDefinitionAdapter,
                                             FunctionalStageDefinitionService functionalStageDefinitionService) {
        this.stageDefinitionAdapter = stageDefinitionAdapter;
        this.functionalStageDefinitionService = functionalStageDefinitionService;
    }

    @Override
    public void onDefinitionEvent(DefinitionUpdateEvent event) {
        System.out.println("Consuming event: " + event);
        switch (event.getStageDefinition().getStageDefinitionType()) {
            case FUNCTIONAL -> onFunctionalDefnition(event);
            case TECHNICAL -> System.out.println("Ignored event when Technical stage related.");
        }
    }

    private void onFunctionalDefnition(DefinitionUpdateEvent event) {
        switch (event.getEventType()) {
            case UPDATE -> {
                FunctionalStageDefinition definition = this.stageDefinitionAdapter.adapte(event.getStageDefinition());
                this.functionalStageDefinitionService.put(definition);
            }
            case DELETE -> this.functionalStageDefinitionService.remove(event.getStageDefinition().getDefinitionId());
        }
    }
}
