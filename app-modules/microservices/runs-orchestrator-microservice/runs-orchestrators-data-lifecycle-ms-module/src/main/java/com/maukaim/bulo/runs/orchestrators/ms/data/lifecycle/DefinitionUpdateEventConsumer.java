package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;

public interface DefinitionUpdateEventConsumer {
    void onDefinitionEvent(StageDefinitionEvent event);
}
