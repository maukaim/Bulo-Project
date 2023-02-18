package com.maukaim.bulo.io.runs.orchestrators.system;

import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunEvent;

public interface FlowRunEventConsumer {
    void onFlowRunEvent(FlowRunEvent flowRunEvent);
}
