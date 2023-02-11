package com.maukaim.bulo.runs.orchestrators.data.runs.flow;

import java.util.Objects;
import java.util.Set;

public class ContextualizedStageDependency {
    private final String inputId;
    private final Set<ContextStageAncestor> contextStageAncestors;

    public ContextualizedStageDependency(String inputId, Set<ContextStageAncestor> contextStageAncestors) {
        this.inputId = inputId;
        this.contextStageAncestors = contextStageAncestors;
    }

    public String getInputId() {
        return inputId;
    }

    public Set<ContextStageAncestor> getAncestors() {
        return contextStageAncestors;
    }

    @Override
    public String toString() {
        return "StageRunDependency{" +
                "inputId='" + inputId + '\'' +
                ", ancestors=" + contextStageAncestors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContextualizedStageDependency that = (ContextualizedStageDependency) o;
        return inputId.equals(that.getInputId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputId);
    }
}