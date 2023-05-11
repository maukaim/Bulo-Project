package com.maukaim.bulo.stages.app.io;

import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageUpdateEventPublisher;

public class StageUpdateEventPublisherImpl implements StageUpdateEventPublisher {
    private final SystemConnector<MicroServiceEventType> systemConnector;

    public StageUpdateEventPublisherImpl(SystemConnector<MicroServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageUpdateEvent event) {
        System.out.println("Publish event: " + event);
        return !this.systemConnector.sendExternal(event, MicroServiceEventType.STAGE_UPDATE)
                .isEmpty();
    }
}
