package com.maukaim.bulo.executors.app.io;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.executors.data.lifecycle.StageRunEventPublisher;
import com.maukaim.bulo.io.executors.system.StageRunEvent;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;

public class DummyStageRunEventPublisher implements StageRunEventPublisher {
    private final SystemConnector<MicroServiceEventType> systemConnector;

    public DummyStageRunEventPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(StageRunEvent event) {
        System.out.println("Publish event : " + event);
        return !this.systemConnector.sendExternal(event, MicroServiceEventType.STAGE_RUN_UPDATE)
                .isEmpty();
    }
}
