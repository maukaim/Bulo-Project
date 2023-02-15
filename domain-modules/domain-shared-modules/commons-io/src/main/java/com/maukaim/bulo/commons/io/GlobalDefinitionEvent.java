package com.maukaim.bulo.commons.io;

import com.maukaim.bulo.io.definitions.shared.DefinitionEventType;
import com.maukaim.bulo.io.shared.ExternalEvent;

public interface GlobalDefinitionEvent extends ExternalEvent {
    DefinitionEventType getEventType();
}
