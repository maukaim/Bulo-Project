package com.maukaim.bulo.runs.orchestrators.data.flow;

import com.maukaim.bulo.commons.models.FlowInterface;

import java.util.Set;

public class Flow implements FlowInterface {
    private final String flowId;
    private final Set<OwnerKey> ownerKeys;
    private final Set<FlowStage> flowStages;
    private final boolean allowParallelRun;
    private final Set<FailureAlternativeRoute> failureAlternativeRoutes;


    public Flow(String flowId,
                Set<OwnerKey> ownerKeys,
                Set<FlowStage> flowStages,
                boolean allowParallelRun,
                Set<FailureAlternativeRoute> failureAlternativeRoutes) {
        this.flowId = flowId;
        this.allowParallelRun = allowParallelRun;
        this.ownerKeys = ownerKeys;
        this.flowStages = flowStages;
        this.failureAlternativeRoutes = failureAlternativeRoutes;
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
                ", allowParallelRun=" + allowParallelRun +
                ", failureAlternativeRoutes=" + failureAlternativeRoutes +
                '}';
    }
}