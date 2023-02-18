package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.runs.orchestrators.events.FlowEvent;

public interface FlowEventConsumer {
    void onFlowEvent(FlowEvent flowEvent);
}
