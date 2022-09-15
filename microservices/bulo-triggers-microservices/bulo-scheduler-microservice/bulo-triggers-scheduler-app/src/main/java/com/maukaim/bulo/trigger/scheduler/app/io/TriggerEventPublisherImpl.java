package com.maukaim.bulo.trigger.scheduler.app.io;

import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;
import com.maukaim.bulo.triggers.io.TriggerEventPublisher;
import com.maukaim.bulo.triggers.io.events.BasicTriggerEvent;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TriggerEventPublisherImpl implements TriggerEventPublisher {
    private final SystemConnector systemConnector;

    public TriggerEventPublisherImpl(SystemConnector systemConnector){
        this.systemConnector = systemConnector;
    }

    @Override
    public void publish(BasicTriggerEvent event) {
        System.out.println("Will publish....");
        this.systemConnector.sendToConsumers(EventType.TRIGGER, event);
    }
}
