package com.maukaim.bulo.runs.orchestrators.io.models.flow;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Objects;
import java.util.Set;

public class InputProviderDto {
    private FlowStageId flowStageId;
    private Set<String> outputIds;

    public InputProviderDto(FlowStageId flowStageId, Set<String> outputIds) {
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
        return "InputProviderDto{" +
                "flowStageId=" + flowStageId +
                ", outputIds=" + outputIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputProviderDto that = (InputProviderDto) o;
        return Objects.equals(flowStageId, that.flowStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowStageId);
    }
}
