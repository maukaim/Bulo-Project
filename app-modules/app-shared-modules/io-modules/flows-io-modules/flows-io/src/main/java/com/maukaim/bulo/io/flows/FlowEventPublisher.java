package com.maukaim.bulo.io.flows;

import com.maukaim.bulo.io.flows.events.FlowEvent;

public interface FlowEventPublisher {
    boolean publish(FlowEvent flowEvent);
}
