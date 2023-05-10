package com.maukaim.bulo.flows.app.io;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.flows.ms.data.lifecycle.FlowEventPublisher;
import com.maukaim.bulo.io.flows.system.FlowEvent;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;

public class FlowEventPublisherImpl implements FlowEventPublisher {
    private final SystemConnector<MicroServiceEventType> systemConnector;

    public FlowEventPublisherImpl(SystemConnector<MicroServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(FlowEvent flowEvent) {
        System.out.println("Publish Event : " + flowEvent);
        return !this.systemConnector.sendExternal(flowEvent, MicroServiceEventType.FLOW_UPDATE)
                .isEmpty();
    }
}
