package com.maukaim.bulo.definitions.data.functional;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Set;

public class InputProvider {
    private final FlowStageId flowStageId;
    private final Set<String> outputIds;

    public InputProvider(FlowStageId flowStageId, Set<String> outputIds) {
        this.flowStageId = flowStageId;
        this.outputIds = outputIds;
    }

    public FlowStageId getFlowStageId() {
        return flowStageId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public String toString() {
        return "InputProvider{" +
                "flowStageId=" + flowStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
