package com.maukaim.bulo.flows.io.flow;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Objects;
import java.util.Set;

public class InputProviderDto {
    private ContextualizedStageId contextualizedStageId;
    private Set<String> outputIds;

    public InputProviderDto(ContextualizedStageId contextualizedStageId, Set<String> outputIds) {
        this.contextualizedStageId = contextualizedStageId;
        this.outputIds = outputIds;
    }

    public ContextualizedStageId getFlowStageId() {
        return contextualizedStageId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public String toString() {
        return "InputProviderDto{" +
                "flowStageId=" + contextualizedStageId +
                ", outputIds=" + outputIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputProviderDto that = (InputProviderDto) o;
        return Objects.equals(contextualizedStageId, that.contextualizedStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextualizedStageId);
    }
}
