package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.runs.orchestrators.events.DefinitionUpdateEvent;

public interface DefinitionUpdateEventConsumer {
    void onDefinitionEvent(DefinitionUpdateEvent event);
}
