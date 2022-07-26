package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.io.FlowEventType;

public interface FlowEventPublisher {
    void publish(Flow flow, FlowEventType flowEventType);
}
