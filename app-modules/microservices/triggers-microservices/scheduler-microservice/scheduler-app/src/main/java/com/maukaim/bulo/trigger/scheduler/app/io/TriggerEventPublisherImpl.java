package com.maukaim.bulo.trigger.scheduler.app.io;

import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.io.triggers.system.TriggerEventPublisher;
import com.maukaim.bulo.io.triggers.system.events.BasicTriggerEvent;

public class TriggerEventPublisherImpl implements TriggerEventPublisher {
    private final SystemConnector<ServiceEventType> systemConnector;

    public TriggerEventPublisherImpl(SystemConnector<ServiceEventType> systemConnector){
        this.systemConnector = systemConnector;
    }

    @Override
    public void publish(BasicTriggerEvent event) {
        System.out.println("Publish event: " + event);
        this.systemConnector.sendExternal(event, ServiceEventType.TRIGGER_FLOW_RUN);
    }
}
