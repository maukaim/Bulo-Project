package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.commons.models.FlowInterface;
import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.flows.io.OwnerKey;

import java.util.Map;
import java.util.Set;

public class Flow implements FlowInterface {
    private final String flowId;
    private final Set<OwnerKey> ownerKeys;
    private final Map<FlowStageId, Set<FlowStageId>> ancestorsByStageId;
    private final boolean allowParallelRun;

    public Flow(String flowId, Set<OwnerKey> ownerKeys, Map<FlowStageId, Set<FlowStageId>> ancestorsByStageId, boolean allowParallelRun) {
        this.flowId = flowId;
        this.ancestorsByStageId = ancestorsByStageId;
        this.allowParallelRun = allowParallelRun;
        this.ownerKeys = ownerKeys;
    }

    public Set<OwnerKey> getFlowOwnerKeys() {
        return ownerKeys;
    }

    public String getFlowId() {
        return flowId;
    }

    public Map<FlowStageId, Set<FlowStageId>> getAncestorsByStageId() {
        return ancestorsByStageId;
    }

    public boolean isParallelRunAllowed() {
        return allowParallelRun;
    }
}