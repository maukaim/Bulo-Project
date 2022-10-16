package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Objects;
import java.util.Set;

public class FlowStageAncestorDto {
    private FlowStageId flowStageId;
    private Set<String> outputIds;

    public FlowStageAncestorDto(FlowStageId flowStageId, Set<String> outputIds) {
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
        FlowStageAncestorDto that = (FlowStageAncestorDto) o;
        return flowStageId.equals(that.flowStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowStageId);
    }

    @Override
    public String toString() {
        return "FlowStageAncestorDto{" +
                "flowStageId=" + flowStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
