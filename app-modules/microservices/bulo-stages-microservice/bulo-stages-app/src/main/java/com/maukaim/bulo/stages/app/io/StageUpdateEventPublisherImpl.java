package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.stages.io.events.StageUpdateEvent;
import com.maukaim.bulo.stages.io.StageUpdateEventPublisher;

public class StageUpdateEventPublisherImpl implements StageUpdateEventPublisher {
    private final SystemConnector<ServiceEventType> systemConnector;

    public StageUpdateEventPublisherImpl(SystemConnector<ServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageUpdateEvent event) {
        System.out.println("Publish event: " + event);
        return !this.systemConnector.sendExternal(event, ServiceEventType.STAGE_UPDATE)
                .isEmpty();
    }
}
