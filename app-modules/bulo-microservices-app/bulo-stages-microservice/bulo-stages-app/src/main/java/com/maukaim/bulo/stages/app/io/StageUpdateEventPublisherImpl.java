package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;
import com.maukaim.bulo.stages.io.events.StageUpdateEvent;
import com.maukaim.bulo.stages.io.StageUpdateEventPublisher;

public class StageUpdateEventPublisherImpl implements StageUpdateEventPublisher {
    private final SystemConnector systemConnector;

    public StageUpdateEventPublisherImpl(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageUpdateEvent event) {
        System.out.println("Publish event: " + event);
        return this.systemConnector.sendToConsumers(EventType.STAGE_UPDATE, event);
    }
}
