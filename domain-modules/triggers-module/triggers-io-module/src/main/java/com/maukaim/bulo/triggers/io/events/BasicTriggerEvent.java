package com.maukaim.bulo.triggers.io.events;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.time.Instant;
import java.util.Set;

public class BasicTriggerEvent {
    private final Instant instant;
    private String flowId;
    private Set<ContextStageId> contextStageIds;

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
