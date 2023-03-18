package com.maukaim.bulo.runs.orchestrators.app.io.publishers;

import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;

public class NeedStageRunCancellationEventPublisherImpl implements NeedStageRunCancellationEventPublisher {
    private final SystemConnector<MicroServiceEventType> systemConnector;

    public NeedStageRunCancellationEventPublisherImpl(SystemConnector<MicroServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(NeedStageRunCancellationEvent event) {
        System.out.println("Publish event: " + event);
        return !this.systemConnector.sendExternal(event, MicroServiceEventType.NEED_STAGE_RUN_CANCEL)
                .isEmpty();
    }
}
