package com.maukaim.bulo.runs.orchestrator.io.in;

import com.maukaim.bulo.commons.io.TriggerEvent;
import com.maukaim.bulo.commons.models.FlowStageId;

import java.time.Instant;
import java.util.Set;

public class BasicTriggerEvent implements TriggerEvent {
    private final Instant instant;
    private String flowId;
    private Set<FlowStageId> flowStageIds;

    public BasicTriggerEvent(String flowId,
                             Set<FlowStageId> flowStageIds,
                             Instant instant) {
        this.flowId = flowId;
        this.flowStageIds = flowStageIds;
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
    public Set<FlowStageId> getFlowStageIds() {
        return flowStageIds;
    }

    @Override
    public String toString() {
        return "BasicTriggerEvent{" +
                "instant=" + instant +
                ", flowId='" + flowId + '\'' +
                ", flowStageIds=" + flowStageIds +
                '}';
    }
}