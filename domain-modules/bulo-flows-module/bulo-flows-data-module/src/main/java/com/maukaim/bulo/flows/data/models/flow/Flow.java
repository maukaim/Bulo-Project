package com.maukaim.bulo.flows.data.models.flow;

import com.maukaim.bulo.commons.models.FlowInterface;

import java.util.Set;

public class Flow implements FlowInterface {
    private final String flowId;
    private final Set<OwnerKey> ownerKeys;
    private final Set<FlowStage> flowStages;
    private final Set<FailureAlternativeRoute> failureAlternativeRoutes;
    private final boolean allowParallelRun;

    public Flow(String flowId,
                Set<OwnerKey> ownerKeys,
                Set<FlowStage> flowStages,
                Set<FailureAlternativeRoute> failureAlternativeRoutes,
                boolean allowParallelRun) {
        this.flowId = flowId;
        this.failureAlternativeRoutes = failureAlternativeRoutes;
        this.allowParallelRun = allowParallelRun;
        this.ownerKeys = ownerKeys;
        this.flowStages = flowStages;
    }

    public Set<OwnerKey> getOwnerKeys() {
        return ownerKeys;
    }

    public String getFlowId() {
        return flowId;
    }

    public Set<FlowStage> getFlowStages() {
        return flowStages;
    }

    public boolean isParallelRunAllowed() {
        return allowParallelRun;
    }

    public Set<FailureAlternativeRoute> getFailureAlternativeRoutes() {
        return failureAlternativeRoutes;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "flowId='" + flowId + '\'' +
                ", ownerKeys=" + ownerKeys +
                ", flowStages=" + flowStages +
                ", failureAlternativeRoutes=" + failureAlternativeRoutes +
                ", allowParallelRun=" + allowParallelRun +
                '}';
    }
}