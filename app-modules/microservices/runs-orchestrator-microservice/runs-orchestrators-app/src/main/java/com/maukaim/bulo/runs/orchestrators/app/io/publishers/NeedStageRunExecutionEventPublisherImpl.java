package com.maukaim.bulo.runs.orchestrators.app.io.publishers;

import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.NeedStageRunExecutionEventPublisher;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunExecutionEvent;

public class NeedStageRunExecutionEventPublisherImpl implements NeedStageRunExecutionEventPublisher {
    private final SystemConnector<MicroServiceEventType> systemConnector;

    public NeedStageRunExecutionEventPublisherImpl(SystemConnector<MicroServiceEventType> systemConnector) {
        this.systemConnector = systemConnector;
    }

    @Override
    public boolean publish(NeedStageRunExecutionEvent needStageRunExecutionEvent) {
        System.out.println("Publish event: "+ needStageRunExecutionEvent);
        return !this.systemConnector.sendExternal(needStageRunExecutionEvent, MicroServiceEventType.NEED_STAGE_RUN_EVENT)
                .isEmpty();
    }
}
