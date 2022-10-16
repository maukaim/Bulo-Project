package com.maukaim.bulo.runs.orchestrators.io;

import com.maukaim.bulo.runs.orchestrators.io.events.FlowRunInstruction;

public interface TriggerEventConsumer {
    String onFlowRunInstruction(FlowRunInstruction triggerEvent);
}
