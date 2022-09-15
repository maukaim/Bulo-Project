package com.maukaim.bulo.runs.orchestrators.app.io.publishers;

import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;
import com.maukaim.bulo.runs.orchestrators.io.FlowRunEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.events.FlowRunEvent;

public class FlowRunEventPublisherImpl implements FlowRunEventPublisher {
    private final SystemConnector systemConnector;

    public FlowRunEventPublisherImpl(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(FlowRunEvent flowRunEvent) {
        System.out.println("Publish event: " + flowRunEvent);
        return this.systemConnector.sendToConsumers(EventType.FLOW_RUN_UPDATE, flowRunEvent);
    }
}
