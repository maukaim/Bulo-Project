package com.maukaim.bulo.runs.orchestrators.app.io.publishers;

import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunCancellationEvent;

public class NeedStageRunCancellationEventPublisherImpl implements NeedStageRunCancellationEventPublisher {
    private final SystemConnector systemConnector;

    public NeedStageRunCancellationEventPublisherImpl(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(NeedStageRunCancellationEvent event) {
        System.out.println("Publish event: " + event);
        return this.systemConnector.sendToConsumers(EventType.NEED_STAGE_RUN_CANCEL, event);
    }
}
