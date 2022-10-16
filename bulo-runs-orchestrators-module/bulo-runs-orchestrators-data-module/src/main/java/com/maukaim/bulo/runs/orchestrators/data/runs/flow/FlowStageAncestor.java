package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Objects;
import java.util.Set;

public class FlowStageAncestor {
    private FlowStageId flowStageId;
    private Set<String> outputIds;

    public FlowStageAncestor(FlowStageId flowStageId, Set<String> outputIds) {
        this.flowStageId = flowStageId;
        this.outputIds = outputIds;
    }

    public FlowStageId getFlowStageId() {
        return flowStageId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStageAncestor that = (FlowStageAncestor) o;
        return flowStageId.equals(that.flowStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowStageId);
    }

    @Override
    public String toString() {
        return "InputProvider{" +
                "flowStageId=" + flowStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
