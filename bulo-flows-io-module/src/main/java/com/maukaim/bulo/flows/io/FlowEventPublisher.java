package com.maukaim.bulo.flows.io;

import com.maukaim.bulo.flows.io.events.FlowEvent;

public interface FlowEventPublisher {
    boolean publish(FlowEvent flowEvent);
}
