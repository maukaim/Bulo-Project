package com.maukaim.bulo.runs.orchestrators.data.runs.stage;


import java.util.Objects;
import java.util.Set;

public class StageRunDependency {
    private final String inputId;
    private final Set<StageRunAncestor> flowStageAncestors;

    public StageRunDependency(String inputId, Set<StageRunAncestor> flowStageAncestors) {
        this.inputId = inputId;
        this.flowStageAncestors = flowStageAncestors;
    }


    public String getInputId() {
        return inputId;
    }

    public Set<StageRunAncestor> getAncestors() {
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
        StageRunDependency that = (StageRunDependency) o;
        return inputId.equals(that.getInputId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputId);
    }
}