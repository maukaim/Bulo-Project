package com.maukaim.bulo.flows.ms.data.lifecycle;

import com.maukaim.bulo.io.flows.system.FlowEvent;

public interface FlowEventConsumer {
    void consume(FlowEvent event);
}
