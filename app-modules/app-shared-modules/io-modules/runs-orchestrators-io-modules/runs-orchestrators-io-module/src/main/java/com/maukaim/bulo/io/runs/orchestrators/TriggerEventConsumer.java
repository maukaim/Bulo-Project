package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.runs.orchestrators.events.FlowRunStartEvent;

public interface TriggerEventConsumer {
    String onFlowRunInstruction(FlowRunStartEvent triggerEvent);
}
