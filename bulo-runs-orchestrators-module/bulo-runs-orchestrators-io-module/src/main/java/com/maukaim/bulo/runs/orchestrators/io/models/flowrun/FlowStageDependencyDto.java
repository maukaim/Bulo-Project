package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;


import java.util.Objects;
import java.util.Set;

public class FlowStageDependencyDto {
    private final String inputId;
    private final Set<FlowStageAncestorDto> ancestors;

    public FlowStageDependencyDto(String inputId, Set<FlowStageAncestorDto> ancestors) {
        this.inputId = inputId;
        this.ancestors = ancestors;
    }


    public String getInputId() {
        return inputId;
    }

    public Set<FlowStageAncestorDto> getAncestors() {
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