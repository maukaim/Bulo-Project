package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.runs.orchestrators.events.FlowRunInstruction;

public interface TriggerEventConsumer {
    String onFlowRunInstruction(FlowRunInstruction triggerEvent);
}
