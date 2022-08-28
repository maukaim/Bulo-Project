package com.maukaim.bulo.flows.io.flow;

import com.maukaim.bulo.commons.models.FlowInterface;

import java.util.Set;

public class FlowDto implements FlowInterface{
    private String flowId;
    private final Set<OwnerKeyDto> ownerKeys;
    private final Set<FlowStageDto> flowStages;
    private final boolean allowParallelRun;

    public FlowDto(String flowId,
                   Set<OwnerKeyDto> ownerKeys,
                   Set<FlowStageDto> flowStages,
                   boolean allowParallelRun) {
        this.flowId = flowId;
        this.ownerKeys = ownerKeys;
        this.flowStages = flowStages;
        this.allowParallelRun = allowParallelRun;
    }

    public void setFlowId(String flowId){
        this.flowId = flowId;
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

    @Override
    public String toString() {
        return "FlowDto{" +
                "flowId='" + flowId + '\'' +
                ", ownerKeys=" + ownerKeys +
                ", flowStages=" + flowStages +
                ", allowParallelRun=" + allowParallelRun +
                '}';
    }
}
