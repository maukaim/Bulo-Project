package com.maukaim.bulo.runs.orchestrators.data.flow;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Objects;
import java.util.Set;

public class FlowStage {
    private final FlowStageId flowStageId;
    private final Set<InputDependency> ioDependencies;

    public FlowStage(FlowStageId flowStageId, Set<InputDependency> ioDependencies) {
        this.flowStageId = flowStageId;
        this.ioDependencies = ioDependencies;
    }

    public FlowStageId getFlowStageId() {
        return flowStageId;
    }

    public Set<InputDependency> getIoDependencies() {
        return ioDependencies;
    }

    @Override
    public String toString() {
        return "FlowStage{" +
                "flowStageId=" + flowStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStage flowStage = (FlowStage) o;
        return flowStageId.equals(flowStage.flowStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowStageId);
    }
}
