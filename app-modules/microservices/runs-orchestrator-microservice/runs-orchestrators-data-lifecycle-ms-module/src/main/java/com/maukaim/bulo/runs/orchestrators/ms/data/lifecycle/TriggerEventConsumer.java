package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunStartEvent;

public interface TriggerEventConsumer {
    String onFlowRunInstruction(FlowRunStartEvent triggerEvent);
}
