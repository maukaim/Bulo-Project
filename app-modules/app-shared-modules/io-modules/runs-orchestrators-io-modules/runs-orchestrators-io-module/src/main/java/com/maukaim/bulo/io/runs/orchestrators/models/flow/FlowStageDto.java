package com.maukaim.bulo.io.runs.orchestrators.models.flow;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Objects;
import java.util.Set;

public class FlowStageDto {
    private final ContextStageId contextStageId;
    private final Set<InputDependencyDto> ioDependencies;

    public FlowStageDto(ContextStageId contextStageId, Set<InputDependencyDto> ioDependencies) {
        this.contextStageId = contextStageId;
        this.ioDependencies = ioDependencies;
    }

    public ContextStageId getFlowStageId() {
        return contextStageId;
    }

    public Set<InputDependencyDto> getIoDependencies() {
        return ioDependencies;
    }

    @Override
    public String toString() {
        return "FlowStageDto{" +
                "flowStageId=" + contextStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStageDto that = (FlowStageDto) o;
        return contextStageId.equals(that.contextStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextStageId);
    }
}
