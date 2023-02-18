package com.maukaim.bulo.scheduler.ms.data.lifecycle;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.io.triggers.TriggerEventPublisher;
import com.maukaim.bulo.io.triggers.events.BasicTriggerEvent;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;

import java.time.Instant;
import java.util.Set;

public class TriggerConnectorImpl implements TriggerConnector {
    private final TriggerEventPublisher triggerEventPublisher;

    public TriggerConnectorImpl(TriggerEventPublisher triggerEventPublisher) {
        this.triggerEventPublisher = triggerEventPublisher;
    }

    @Override
    public void requestTrigger(String flowId, Set<ContextStageId> contextStageIds) {
        this.triggerEventPublisher.publish(new BasicTriggerEvent(
                flowId, contextStageIds, Instant.now()
        ));
    }
}
