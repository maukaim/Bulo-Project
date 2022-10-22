package com.maukaim.bulo.commons.models;

import java.util.Objects;
import java.util.Set;

public class TriggerId {
    private final String flowId;
    private final Set<ContextualizedStageId> contextualizedStageIds;

    public static TriggerId of(String flowId, Set<ContextualizedStageId> contextualizedStageIds){
        return new TriggerId(flowId, contextualizedStageIds);
    }

    public TriggerId(String flowId, Set<ContextualizedStageId> contextualizedStageIds) {
        this.flowId = flowId;
        this.contextualizedStageIds = contextualizedStageIds;
    }

    public String getFlowId() {
        return flowId;
    }

    public Set<ContextualizedStageId> getFlowStageIds() {
        return contextualizedStageIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriggerId triggerId = (TriggerId) o;
        return flowId.equals(triggerId.flowId) && Objects.equals(contextualizedStageIds, triggerId.contextualizedStageIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowId, contextualizedStageIds);
    }

    @Override
    public String toString() {
        return "TriggerId{" +
                "flowId='" + flowId + '\'' +
                ", stageIds=" + contextualizedStageIds +
                '}';
    }
}
