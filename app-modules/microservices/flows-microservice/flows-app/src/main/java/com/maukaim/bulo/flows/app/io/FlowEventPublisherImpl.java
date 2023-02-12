package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.flows.io.FlowEventPublisher;
import com.maukaim.bulo.flows.io.events.FlowEvent;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;

public class FlowEventPublisherImpl implements FlowEventPublisher {
    private final SystemConnector<ServiceEventType> systemConnector;

    public FlowEventPublisherImpl(SystemConnector<ServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(FlowEvent flowEvent) {
        System.out.println("Publish Event : " + flowEvent);
        return !this.systemConnector.sendExternal(flowEvent, ServiceEventType.FLOW_UPDATE)
                .isEmpty();
    }
}
