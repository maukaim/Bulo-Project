package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.io.executors.system.StageRunEventPublisher;
import com.maukaim.bulo.io.executors.system.out.StageRunEvent;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;

public class DummyStageRunEventPublisher implements StageRunEventPublisher {
    private final SystemConnector<ServiceEventType> systemConnector;

    public DummyStageRunEventPublisher(SystemConnector<ServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageRunEvent event) {
        System.out.println("Publish event : " + event);
        return !this.systemConnector.sendExternal(event, ServiceEventType.STAGE_RUN_UPDATE)
                .isEmpty();
    }
}
