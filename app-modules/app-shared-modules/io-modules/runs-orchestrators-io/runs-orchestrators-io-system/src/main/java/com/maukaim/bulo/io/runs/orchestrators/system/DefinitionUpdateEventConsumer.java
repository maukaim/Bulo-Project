package com.maukaim.bulo.io.runs.orchestrators.system;

import com.maukaim.bulo.io.runs.orchestrators.system.events.DefinitionUpdateEvent;

public interface DefinitionUpdateEventConsumer {
    void onDefinitionEvent(DefinitionUpdateEvent event);
}
