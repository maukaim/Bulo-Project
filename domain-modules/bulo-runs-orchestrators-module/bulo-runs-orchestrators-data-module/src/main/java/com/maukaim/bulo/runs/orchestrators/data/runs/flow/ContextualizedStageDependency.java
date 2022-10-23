package com.maukaim.bulo.runs.orchestrators.data.runs.flow;


import java.util.Objects;
import java.util.Set;

public class ContextualizedStageDependency {
    private final String inputId;
    private final Set<FlowStageAncestor> flowStageAncestors;

    public ContextualizedStageDependency(String inputId, Set<FlowStageAncestor> flowStageAncestors) {
        this.inputId = inputId;
        this.flowStageAncestors = flowStageAncestors;
    }


    public String getInputId() {
        return inputId;
    }

    public Set<FlowStageAncestor> getAncestors() {
        return flowStageAncestors;
    }

    @Override
    public String toString() {
        return "StageRunDependency{" +
                "inputId='" + inputId + '\'' +
                ", ancestors=" + flowStageAncestors +
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