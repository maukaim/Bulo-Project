package com.maukaim.bulo.trigger.scheduler.app.io;

import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.io.triggers.system.TriggerEventPublisher;
import com.maukaim.bulo.io.triggers.system.events.BasicTriggerEvent;

public class TriggerEventPublisherImpl implements TriggerEventPublisher {
    private final SystemConnector<MicroServiceEventType> systemConnector;

    public TriggerEventPublisherImpl(SystemConnector<MicroServiceEventType> systemConnector){
        this.systemConnector = systemConnector;
    }

    @Override
    public void publish(BasicTriggerEvent event) {
        System.out.println("Publish event: " + event);
        this.systemConnector.sendExternal(event, MicroServiceEventType.TRIGGER_FLOW_RUN);
    }
}
