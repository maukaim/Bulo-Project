package com.maukaim.bulo.runs.orchestrators.app.io.publishers;

import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.FlowRunEventPublisher;
import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunEvent;

public class FlowRunEventPublisherImpl implements FlowRunEventPublisher {
    private final SystemConnector<MicroServiceEventType> systemConnector;

    public FlowRunEventPublisherImpl(SystemConnector<MicroServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(FlowRunEvent flowRunEvent) {
        System.out.println("Publish event: " + flowRunEvent);
        return !this.systemConnector.sendExternal(flowRunEvent, MicroServiceEventType.FLOW_RUN_UPDATE)
                .isEmpty();
    }
}
