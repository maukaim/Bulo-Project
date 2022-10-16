package com.maukaim.bulo.trigger.scheduler.app.io;

import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;
import com.maukaim.bulo.triggers.io.TriggerEventPublisher;
import com.maukaim.bulo.triggers.io.events.BasicTriggerEvent;

public class TriggerEventPublisherImpl implements TriggerEventPublisher {
    private final SystemConnector systemConnector;

    public TriggerEventPublisherImpl(SystemConnector systemConnector){
        this.systemConnector = systemConnector;
    }

    @Override
    public void publish(BasicTriggerEvent event) {
        System.out.println("Publish event: " + event);
        this.systemConnector.sendToConsumers(EventType.TRIGGER, event);
    }
}
