package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.executors.io.StageRunResultEventPublisher;
import com.maukaim.bulo.executors.io.out.StageRunResultEvent;
import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;

public class StageRunResultEventPublisherImpl implements StageRunResultEventPublisher {
    private final SystemConnector systemConnector;

    public StageRunResultEventPublisherImpl(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageRunResultEvent event) {
        System.out.println("Publish event : " + event);
        return this.systemConnector.sendToConsumers(EventType.STAGE_RUN_RESULT, event);
    }
}
