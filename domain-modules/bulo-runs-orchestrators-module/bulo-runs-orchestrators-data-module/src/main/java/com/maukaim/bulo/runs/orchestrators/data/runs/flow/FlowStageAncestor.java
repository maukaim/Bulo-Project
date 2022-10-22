package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Objects;
import java.util.Set;

public class FlowStageAncestor {
    private ContextualizedStageId contextualizedStageId;
    private Set<String> outputIds;

    public FlowStageAncestor(ContextualizedStageId contextualizedStageId, Set<String> outputIds) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStageAncestor that = (FlowStageAncestor) o;
        return contextualizedStageId.equals(that.contextualizedStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextualizedStageId);
    }

    @Override
    public String toString() {
        return "InputProvider{" +
                "flowStageId=" + contextualizedStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
