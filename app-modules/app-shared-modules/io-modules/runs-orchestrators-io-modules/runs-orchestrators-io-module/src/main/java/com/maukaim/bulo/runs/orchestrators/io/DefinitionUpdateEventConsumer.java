package com.maukaim.bulo.runs.orchestrators.io;

import com.maukaim.bulo.runs.orchestrators.io.events.DefinitionUpdateEvent;

public interface DefinitionUpdateEventConsumer {
    void onDefinitionEvent(DefinitionUpdateEvent event);
}
