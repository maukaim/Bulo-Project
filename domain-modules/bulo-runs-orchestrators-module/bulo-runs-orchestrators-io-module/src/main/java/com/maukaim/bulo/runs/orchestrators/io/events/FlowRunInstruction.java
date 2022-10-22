package com.maukaim.bulo.runs.orchestrators.io.events;

import com.maukaim.bulo.commons.io.TriggerEvent;
import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.time.Instant;
import java.util.Set;

public class FlowRunInstruction implements TriggerEvent {
    private final Instant instant;
    private String flowId;
    private Set<ContextualizedStageId> contextualizedStageIds;

    public FlowRunInstruction(String flowId,
                              Set<ContextualizedStageId> contextualizedStageIds,
                              Instant instant) {
        this.flowId = flowId;
        this.contextualizedStageIds = contextualizedStageIds;
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
    public Set<ContextualizedStageId> getFlowStageIds() {
        return contextualizedStageIds;
    }

    @Override
    public String toString() {
        return "BasicTriggerEvent{" +
                "instant=" + instant +
                ", flowId='" + flowId + '\'' +
                ", flowStageIds=" + contextualizedStageIds +
                '}';
    }
}