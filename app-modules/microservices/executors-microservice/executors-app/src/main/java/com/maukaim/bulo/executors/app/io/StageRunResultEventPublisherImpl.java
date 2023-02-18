package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.executors.data.lifecycle.StageRunResultEventPublisher;
import com.maukaim.bulo.io.executors.system.StageRunResultEvent;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;

public class StageRunResultEventPublisherImpl implements StageRunResultEventPublisher {
    private final SystemConnector<ServiceEventType> systemConnector;

    public StageRunResultEventPublisherImpl(SystemConnector<ServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageRunResultEvent event) {
        System.out.println("Publish event : " + event);
        return !this.systemConnector.sendExternal(event, ServiceEventType.STAGE_RUN_RESULT)
                .isEmpty();
    }
}
