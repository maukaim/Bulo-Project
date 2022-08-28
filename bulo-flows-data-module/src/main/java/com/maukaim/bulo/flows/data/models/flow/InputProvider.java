package com.maukaim.bulo.flows.data.models.flow;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Objects;
import java.util.Set;

public class InputProvider {
    private FlowStageId flowStageId;
    private Set<String> outputIds;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputProvider that = (InputProvider) o;
        return flowStageId.equals(that.flowStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowStageId);
    }
}
