package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowEvent;

public interface FlowEventConsumer {
    void onFlowEvent(FlowEvent flowEvent);
}
