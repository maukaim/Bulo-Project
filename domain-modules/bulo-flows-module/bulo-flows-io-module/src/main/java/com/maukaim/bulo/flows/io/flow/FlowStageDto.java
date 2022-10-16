package com.maukaim.bulo.flows.io.flow;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Objects;
import java.util.Set;

public class FlowStageDto {
    private final FlowStageId flowStageId;
    private final Set<IoDependencyDto> ioDependencies;

    public FlowStageDto(FlowStageId flowStageId, Set<IoDependencyDto> ioDependencies) {
        this.flowStageId = flowStageId;
        this.ioDependencies = ioDependencies;
    }

    public FlowStageId getFlowStageId() {
        return flowStageId;
    }

    public Set<IoDependencyDto> getIoDependencies() {
        return ioDependencies;
    }

    @Override
    public String toString() {
        return "FlowStageDto{" +
                "flowStageId=" + flowStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStageDto that = (FlowStageDto) o;
        return flowStageId.equals(that.flowStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowStageId);
    }
}
