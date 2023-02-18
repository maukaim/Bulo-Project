package com.maukaim.bulo.io.runs.orchestrators.system;

import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunEvent;

public interface FlowRunEventPublisher {
    boolean publish(FlowRunEvent flowRunEvent);
}
