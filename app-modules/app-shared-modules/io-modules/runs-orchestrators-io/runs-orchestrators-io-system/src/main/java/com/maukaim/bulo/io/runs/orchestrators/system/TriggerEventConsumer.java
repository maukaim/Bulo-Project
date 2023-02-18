package com.maukaim.bulo.io.runs.orchestrators.system;

import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunStartEvent;

public interface TriggerEventConsumer {
    String onFlowRunInstruction(FlowRunStartEvent triggerEvent);
}
