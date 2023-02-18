package com.maukaim.bulo.io.flows.system;

import com.maukaim.bulo.io.flows.system.events.FlowEvent;

public interface FlowEventPublisher {
    boolean publish(FlowEvent flowEvent);
}
