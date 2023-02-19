package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunEvent;

public interface FlowRunEventPublisher {
    boolean publish(FlowRunEvent flowRunEvent);
}
