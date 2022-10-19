package com.maukaim.bulo.definitions.data.functional;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Set;

public class FsStage {
    private final FlowStageId flowStageId;
    private final Set<IoDependency> ioDependencies;

    public FsStage(FlowStageId flowStageId, Set<IoDependency> ioDependencies) {
        this.flowStageId = flowStageId;
        this.ioDependencies = ioDependencies;
    }

    public FlowStageId getFlowStageId() {
        return flowStageId;
    }

    public Set<IoDependency> getIoDependencies() {
        return ioDependencies;
    }

    @Override
    public String toString() {
        return "FsStage{" +
                "flowStageId=" + flowStageId +
                ", ioDependencies=" + ioDependencies +
                '}';
    }
}
