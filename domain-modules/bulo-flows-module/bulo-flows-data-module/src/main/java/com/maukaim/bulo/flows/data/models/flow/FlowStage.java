package com.maukaim.bulo.flows.data.models.flow;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Objects;
import java.util.Set;

public class FlowStage {
    private final ContextualizedStageId contextualizedStageId;
    private final Set<IoDependency> ioDependencies;

    public FlowStage(ContextualizedStageId contextualizedStageId, Set<IoDependency> ioDependencies) {
        this.contextualizedStageId = contextualizedStageId;
        this.ioDependencies = ioDependencies;
    }

    public ContextualizedStageId getFlowStageId() {
        return contextualizedStageId;
    }

    public Set<IoDependency> getIoDependencies() {
        return ioDependencies;
    }

    @Override
    public String toString() {
        return "FlowStage{" +
                "flowStageId=" + contextualizedStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStage flowStage = (FlowStage) o;
        return contextualizedStageId.equals(flowStage.contextualizedStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextualizedStageId);
    }
}
