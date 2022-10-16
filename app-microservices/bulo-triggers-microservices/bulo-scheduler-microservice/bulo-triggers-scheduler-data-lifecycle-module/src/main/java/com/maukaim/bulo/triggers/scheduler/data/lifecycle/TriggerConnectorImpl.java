package com.maukaim.bulo.triggers.scheduler.data.lifecycle;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.triggers.io.TriggerEventPublisher;
import com.maukaim.bulo.triggers.io.events.BasicTriggerEvent;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;

import java.time.Instant;
import java.util.Set;

public class TriggerConnectorImpl implements TriggerConnector {
    private final TriggerEventPublisher triggerEventPublisher;

    public TriggerConnectorImpl(TriggerEventPublisher triggerEventPublisher) {
        this.triggerEventPublisher = triggerEventPublisher;
    }

    @Override
    public void requestTrigger(String flowId, Set<FlowStageId> flowStageIds) {
        this.triggerEventPublisher.publish(new BasicTriggerEvent(
                flowId, flowStageIds, Instant.now()
        ));
    }
}
