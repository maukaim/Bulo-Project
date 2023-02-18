package com.maukaim.bulo.io.flows.system.flow;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Objects;
import java.util.Set;

public class InputProviderDto {
    private ContextStageId contextStageId;
    private Set<String> outputIds;

    public InputProviderDto(ContextStageId contextStageId, Set<String> outputIds) {
        this.contextStageId = contextStageId;
        this.outputIds = outputIds;
    }

    public ContextStageId getFlowStageId() {
        return contextStageId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public String toString() {
        return "InputProviderDto{" +
                "flowStageId=" + contextStageId +
                ", outputIds=" + outputIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputProviderDto that = (InputProviderDto) o;
        return Objects.equals(contextStageId, that.contextStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextStageId);
    }
}
