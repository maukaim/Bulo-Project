package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.executors.data.lifecycle.StageRunResultEventPublisher;
import com.maukaim.bulo.io.executors.system.StageRunResultEvent;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;

public class StageRunResultEventPublisherImpl implements StageRunResultEventPublisher {
    private final SystemConnector<MicroServiceEventType> systemConnector;

    public StageRunResultEventPublisherImpl(SystemConnector<MicroServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageRunResultEvent event) {
        System.out.println("Publish event : " + event);
        return !this.systemConnector.sendExternal(event, MicroServiceEventType.STAGE_RUN_RESULT)
                .isEmpty();
    }
}
