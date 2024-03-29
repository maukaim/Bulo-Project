package com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun;

import java.util.Objects;
import java.util.Set;

public class FlowStageDependencyDto {
    private final String inputId;
    private final Set<StageRunAncestorDto> ancestors;

    public FlowStageDependencyDto(String inputId, Set<StageRunAncestorDto> ancestors) {
        this.inputId = inputId;
        this.ancestors = ancestors;
    }

    public String getInputId() {
        return inputId;
    }

    public Set<StageRunAncestorDto> getAncestors() {
        return ancestors;
    }

    @Override
    public String toString() {
        return "FlowStageDependencyDto{" +
                "inputId='" + inputId + '\'' +
                ", ancestors=" + ancestors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowStageDependencyDto that = (FlowStageDependencyDto) o;
        return inputId.equals(that.getInputId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputId);
    }
}