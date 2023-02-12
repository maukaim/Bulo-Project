package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.commons.io.TriggerEvent;
import com.maukaim.bulo.commons.models.ContextStageId;

import java.time.Instant;
import java.util.Set;

public class FlowRunInstruction implements TriggerEvent {
    private final Instant instant;
    private String flowId;
    private Set<ContextStageId> contextStageIds;

    public FlowRunInstruction(String flowId,
                              Set<ContextStageId> contextStageIds,
                              Instant instant) {
        this.flowId = flowId;
        this.contextStageIds = contextStageIds;
        this.instant = instant;
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public String getFlowId() {
        return flowId;
    }

    @Override
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