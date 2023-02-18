package com.maukaim.bulo.io.runs.orchestrators;

import com.maukaim.bulo.io.runs.orchestrators.events.FlowRunEvent;

public interface FlowRunEventPublisher {
    boolean publish(FlowRunEvent flowRunEvent);
}
