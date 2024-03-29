package com.maukaim.bulo.io.triggers.system.events;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.time.Instant;
import java.util.Set;

public class BasicTriggerEvent {
    private final Instant instant;
    private final String flowId;
    private final Set<ContextStageId> contextStageIds;

    public BasicTriggerEvent(String flowId,
                             Set<ContextStageId> contextStageIds,
                             Instant instant) {
        this.flowId = flowId;
        this.contextStageIds = contextStageIds;
        this.instant = instant;
    }

    public Instant getInstant() {
        return instant;
    }

    public String getFlowId() {
        return flowId;
    }

    public Set<ContextStageId> getFlowStageIds() {
        return contextStageIds;
    }

    @Override
    public String toString() {
        return "BasicTriggerEvent{" +
                "instant=" + instant +
                ", flowId='" + flowId + '\'' +
                ", flowStageIds=" + contextStageIds +
                '}';
    }
}
