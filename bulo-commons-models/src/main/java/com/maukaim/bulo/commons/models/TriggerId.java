package com.maukaim.bulo.commons.models;

import java.util.Objects;
import java.util.Set;

public class TriggerId {
    private final String flowId;
    private final Set<FlowStageId> flowStageIds;

    public static TriggerId of(String flowId, Set<FlowStageId> flowStageIds){
        return new TriggerId(flowId, flowStageIds);
    }

    public TriggerId(String flowId, Set<FlowStageId> flowStageIds) {
        this.flowId = flowId;
        this.flowStageIds = flowStageIds;
    }

    public String getFlowId() {
        return flowId;
    }

    public Set<FlowStageId> getFlowStageIds() {
        return flowStageIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriggerId triggerId = (TriggerId) o;
        return flowId.equals(triggerId.flowId) && Objects.equals(flowStageIds, triggerId.flowStageIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowId, flowStageIds);
    }

    @Override
    public String toString() {
        return "TriggerId{" +
                "flowId='" + flowId + '\'' +
                ", stageIds=" + flowStageIds +
                '}';
    }
}
