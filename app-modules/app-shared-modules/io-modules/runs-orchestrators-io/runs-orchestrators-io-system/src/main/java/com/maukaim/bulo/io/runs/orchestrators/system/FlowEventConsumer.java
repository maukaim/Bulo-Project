package com.maukaim.bulo.io.runs.orchestrators.system;

import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowEvent;

public interface FlowEventConsumer {
    void onFlowEvent(FlowEvent flowEvent);
}
