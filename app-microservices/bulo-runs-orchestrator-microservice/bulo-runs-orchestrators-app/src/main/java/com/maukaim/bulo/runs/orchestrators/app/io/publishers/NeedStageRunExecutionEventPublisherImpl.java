package com.maukaim.bulo.runs.orchestrators.app.io.publishers;

import com.maukaim.bulo.ms.connectivity.EventType;
import com.maukaim.bulo.ms.connectivity.SystemConnector;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunExecutionEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.events.NeedStageRunExecutionEvent;

public class NeedStageRunExecutionEventPublisherImpl implements NeedStageRunExecutionEventPublisher {
    private final SystemConnector systemConnector;

    public NeedStageRunExecutionEventPublisherImpl(SystemConnector systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(NeedStageRunExecutionEvent needStageRunExecutionEvent) {
        System.out.println("Publish event: "+ needStageRunExecutionEvent);
        return this.systemConnector.sendToConsumers(EventType.NEED_STAGE_RUN_EVENT, needStageRunExecutionEvent);
    }
}
