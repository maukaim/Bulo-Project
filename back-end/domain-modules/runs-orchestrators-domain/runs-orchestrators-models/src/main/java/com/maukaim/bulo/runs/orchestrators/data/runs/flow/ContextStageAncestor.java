package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Objects;
import java.util.Set;

public class ContextStageAncestor {
    private final ContextStageId contextStageId;
    private final Set<String> outputIds;

    public ContextStageAncestor(ContextStageId contextStageId, Set<String> outputIds) {
        this.contextStageId = contextStageId;
        this.outputIds = outputIds;
    }

    public ContextStageId getContextStageId() {
        return contextStageId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContextStageAncestor that = (ContextStageAncestor) o;
        return contextStageId.equals(that.contextStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextStageId);
    }

    @Override
    public String toString() {
        return "InputProvider{" +
                "flowStageId=" + contextStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
