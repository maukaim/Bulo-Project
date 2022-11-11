package com.maukaim.bulo.runs.orchestrators.data.flow;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Objects;
import java.util.Set;

public class FlowStage {
    private final ContextStageId contextStageId;
    private final Set<InputDependency> ioDependencies;

    public FlowStage(ContextStageId contextStageId, Set<InputDependency> ioDependencies) {
        this.contextStageId = contextStageId;
        this.ioDependencies = ioDependencies;
    }

    public ContextStageId getFlowStageId() {
        return contextStageId;
    }

    public Set<InputDependency> getIoDependencies() {
        return ioDependencies;
    }

    @Override
    public String toString() {
        return "FlowStage{" +
                "flowStageId=" + contextStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStage flowStage = (FlowStage) o;
        return contextStageId.equals(flowStage.contextStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextStageId);
    }
}
