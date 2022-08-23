package com.maukaim.bulo.runs.orchestrators.io;


import com.maukaim.bulo.runs.orchestrators.io.both.FlowRunEvent;

public interface FlowRunEventConsumer {
    void onFlowRunEvent(FlowRunEvent flowRunEvent);
}
