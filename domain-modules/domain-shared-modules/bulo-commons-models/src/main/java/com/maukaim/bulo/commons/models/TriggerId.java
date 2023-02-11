package com.maukaim.bulo.commons.models;

import java.util.Objects;
import java.util.Set;

public class TriggerId {
    private final String flowId;
    private final Set<ContextStageId> contextStageIds;

    public static TriggerId of(String flowId, Set<ContextStageId> contextStageIds){
        return new TriggerId(flowId, contextStageIds);
    }

    public TriggerId(String flowId, Set<ContextStageId> contextStageIds) {
        this.flowId = flowId;
        this.contextStageIds = contextStageIds;
    }

    public String getFlowId() {
        return flowId;
    }

    public Set<ContextStageId> getFlowStageIds() {
        return contextStageIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriggerId triggerId = (TriggerId) o;
        return flowId.equals(triggerId.flowId) && Objects.equals(contextStageIds, triggerId.contextStageIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowId, contextStageIds);
    }

    @Override
    public String toString() {
        return "TriggerId{" +
                "flowId='" + flowId + '\'' +
                ", stageIds=" + contextStageIds +
                '}';
    }
}
