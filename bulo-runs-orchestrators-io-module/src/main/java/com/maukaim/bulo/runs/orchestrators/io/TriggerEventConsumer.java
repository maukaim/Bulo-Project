package com.maukaim.bulo.runs.orchestrators.io;

import com.maukaim.bulo.runs.orchestrators.io.in.BasicTriggerEvent;

public interface TriggerEventConsumer {
    String onTriggerEvent(BasicTriggerEvent triggerEvent);
}
