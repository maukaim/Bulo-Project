package com.maukaim.bulo.flows.io.flow;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Objects;
import java.util.Set;

public class FlowStageDto {
    private final ContextualizedStageId contextualizedStageId;
    private final Set<IoDependencyDto> ioDependencies;

    public FlowStageDto(ContextualizedStageId contextualizedStageId, Set<IoDependencyDto> ioDependencies) {
        this.contextualizedStageId = contextualizedStageId;
        this.ioDependencies = ioDependencies;
    }

    public ContextualizedStageId getFlowStageId() {
        return contextualizedStageId;
    }

    public Set<IoDependencyDto> getIoDependencies() {
        return ioDependencies;
    }

    @Override
    public String toString() {
        return "FlowStageDto{" +
                "flowStageId=" + contextualizedStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStageDto that = (FlowStageDto) o;
        return contextualizedStageId.equals(that.contextualizedStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextualizedStageId);
    }
}
