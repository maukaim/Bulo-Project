package com.maukaim.bulo.runs.orchestrators.io;

import com.maukaim.bulo.runs.orchestrators.io.events.BasicTriggerEvent;

public interface TriggerEventConsumer {
    String onTriggerEvent(BasicTriggerEvent triggerEvent);
}
