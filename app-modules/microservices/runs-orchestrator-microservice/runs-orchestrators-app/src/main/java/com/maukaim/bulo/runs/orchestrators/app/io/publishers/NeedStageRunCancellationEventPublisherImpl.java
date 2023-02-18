package com.maukaim.bulo.runs.orchestrators.app.io.publishers;

import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.io.runs.orchestrators.system.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;

public class NeedStageRunCancellationEventPublisherImpl implements NeedStageRunCancellationEventPublisher {
    private final SystemConnector<ServiceEventType> systemConnector;

    public NeedStageRunCancellationEventPublisherImpl(SystemConnector<ServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(NeedStageRunCancellationEvent event) {
        System.out.println("Publish event: " + event);
        return !this.systemConnector.sendExternal(event, ServiceEventType.NEED_STAGE_RUN_CANCEL)
                .isEmpty();
    }
}
