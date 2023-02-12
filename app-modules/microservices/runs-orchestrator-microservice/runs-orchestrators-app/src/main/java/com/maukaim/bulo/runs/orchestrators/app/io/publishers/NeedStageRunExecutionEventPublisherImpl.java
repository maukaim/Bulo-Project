package com.maukaim.bulo.runs.orchestrators.app.io.publishers;

import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunExecutionEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunExecutionEvent;

public class NeedStageRunExecutionEventPublisherImpl implements NeedStageRunExecutionEventPublisher {
    private final SystemConnector<ServiceEventType> systemConnector;

    public NeedStageRunExecutionEventPublisherImpl(SystemConnector<ServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(NeedStageRunExecutionEvent needStageRunExecutionEvent) {
        System.out.println("Publish event: "+ needStageRunExecutionEvent);
        return !this.systemConnector.sendExternal(needStageRunExecutionEvent, ServiceEventType.NEED_STAGE_RUN_EVENT)
                .isEmpty();
    }
}
