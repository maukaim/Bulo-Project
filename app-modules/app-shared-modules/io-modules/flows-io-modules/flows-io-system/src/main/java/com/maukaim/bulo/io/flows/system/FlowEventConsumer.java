package com.maukaim.bulo.io.flows.system;

import com.maukaim.bulo.io.flows.system.events.FlowEvent;

public interface FlowEventConsumer {
    void onFlowEvent(FlowEvent event);
}
