package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.runs.orchestrators.events.FlowRunEvent;

public interface FlowRunEventConsumer {
    void onFlowRunEvent(FlowRunEvent flowRunEvent);
}
