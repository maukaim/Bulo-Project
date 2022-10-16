package com.maukaim.bulo.runs.orchestrators.io;


import com.maukaim.bulo.runs.orchestrators.io.events.FlowEvent;

public interface FlowEventConsumer {
    void onFlowEvent(FlowEvent flowEvent);
}
