package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.io.flows.system.FlowEvent;

public interface FlowEventConsumer {
    void onFlowEvent(FlowEvent flowEvent);
}
