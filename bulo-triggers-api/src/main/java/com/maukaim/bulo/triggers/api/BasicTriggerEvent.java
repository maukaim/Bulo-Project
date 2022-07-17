package com.maukaim.bulo.triggers.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maukaim.bulo.common.io.TriggerEvent;
import com.maukaim.bulo.commons.core.models.FlowStageId;

import java.time.Instant;
import java.util.Set;

public class BasicTriggerEvent implements TriggerEvent {
    private final Instant instant;
    private String flowId;
    private Set<FlowStageId> flowStageIds;

    public BasicTriggerEvent(@JsonProperty("flowId") String flowId,
                             @JsonProperty("flowStageIds") Set<FlowStageId> flowStageIds,
                             @JsonProperty("instant") Instant instant) {
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
