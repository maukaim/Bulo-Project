package com.maukaim.bulo.runs.orchestrators.io.models.flow;

import com.maukaim.bulo.commons.models.FlowInterface;

import java.util.Set;

public class FlowDto implements FlowInterface{
    private String flowId;
    private final Set<OwnerKeyDto> ownerKeys;
    private final Set<FlowStageDto> flowStages;
    private final boolean allowParallelRun;
    private final Set<FailureAlternativeRoutesDto> failureAlternativeRoutes;

    public FlowDto(String flowId,
                   Set<OwnerKeyDto> ownerKeys,
                   Set<FlowStageDto> flowStages,
                   boolean allowParallelRun,
                   Set<FailureAlternativeRoutesDto> failureAlternativeRoutes) {
        this.flowId = flowId;
        this.ownerKeys = ownerKeys;
        this.flowStages = flowStages;
        this.allowParallelRun = allowParallelRun;
        this.failureAlternativeRoutes = failureAlternativeRoutes;
    }

    public String getFlowId() {
        return flowId;
    }

    public Set<OwnerKeyDto> getOwnerKeys() {
        return ownerKeys;
    }

    public Set<FlowStageDto> getFlowStages() {
        return flowStages;
    }

    public boolean isParallelRunAllowed() {
        return allowParallelRun;
    }

    public Set<FailureAlternativeRoutesDto> getFailureAlternativeRoutes() {
        return failureAlternativeRoutes;
    }

    @Override
    public String toString() {
        return "FlowDto{" +
                "flowId='" + flowId + '\'' +
                ", ownerKeys=" + ownerKeys +
                ", flowStages=" + flowStages +
                ", allowParallelRun=" + allowParallelRun +
                ", failureAlternativeRoutes=" + failureAlternativeRoutes +
                '}';
    }
}
