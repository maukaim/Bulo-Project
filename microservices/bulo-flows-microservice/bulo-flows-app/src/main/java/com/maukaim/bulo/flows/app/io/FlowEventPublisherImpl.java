package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.flows.io.FlowEventPublisher;
import com.maukaim.bulo.flows.io.events.FlowEvent;
import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;

public class FlowEventPublisherImpl implements FlowEventPublisher {
    private final SystemConnector systemConnector;

    public FlowEventPublisherImpl(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(FlowEvent flowEvent) {
        System.out.println("Publish Event : " + flowEvent);
        return this.systemConnector.sendToConsumers(EventType.FLOW_UPDATE, flowEvent);
    }
}
