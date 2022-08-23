package com.maukaim.bulo.runs.orchestrators.io;


import com.maukaim.bulo.runs.orchestrators.io.both.FlowRunEvent;

public interface FlowRunEventPublisher {
    boolean publish(FlowRunEvent flowRunEvent);
}
