package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.executors.io.StageRunEventPublisher;
import com.maukaim.bulo.executors.io.out.StageRunEvent;
import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;

public class DummyStageRunEventPublisher implements StageRunEventPublisher {
    private final SystemConnector systemConnector;

    public DummyStageRunEventPublisher(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageRunEvent event) {
        System.out.println("Publish event : " + event);
        return this.systemConnector.sendToConsumers(EventType.STAGE_RUN_UPDATE, event);
    }
}
