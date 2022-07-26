package com.maukaim.bulo.flows.io;

import com.maukaim.bulo.commons.models.FlowInterface;
import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Map;
import java.util.Set;

public class FlowData implements FlowInterface{
    private final String flowId;
    private final Set<OwnerKey> ownerKeys;
    private final Map<FlowStageId,Set<FlowStageId>> ancestorsByStageId;
    private final boolean allowParallelRun;

    public FlowData(String flowId, Set<OwnerKey> ownerKeys, Map<FlowStageId, Set<FlowStageId>> ancestorsByStageId, boolean allowParallelRun) {
        this.flowId = flowId;
        this.ownerKeys = ownerKeys;
        this.ancestorsByStageId = ancestorsByStageId;
        this.allowParallelRun = allowParallelRun;
    }

    public String getFlowId() {
        return flowId;
    }

    public Set<OwnerKey> getFlowOwners() {
        return ownerKeys;
    }

    public boolean isParallelRunAllowed() {
        return allowParallelRun;
    }

    public Map<FlowStageId, Set<FlowStageId>> getAncestorsByStageId() {
        return ancestorsByStageId;
    }
}
