package com.maukaim.bulo.io.flows;

import com.maukaim.bulo.io.flows.events.FlowEvent;

public interface FlowEventConsumer {
    void onFlowEvent(FlowEvent event);
}
