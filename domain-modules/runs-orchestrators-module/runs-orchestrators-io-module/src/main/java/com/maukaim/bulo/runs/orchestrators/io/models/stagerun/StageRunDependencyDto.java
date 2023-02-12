package com.maukaim.bulo.runs.orchestrators.io.models.stagerun;

import java.util.Objects;
import java.util.Set;

public class StageRunDependencyDto {
    private final String inputId;
    private final Set<StageRunAncestorDto> ancestors;

    public StageRunDependencyDto(String inputId, Set<StageRunAncestorDto> ancestors) {
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
        return "StageRunDependencyDto{" +
                "inputId='" + inputId + '\'' +
                ", ancestors=" + ancestors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageRunDependencyDto that = (StageRunDependencyDto) o;
        return inputId.equals(that.getInputId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputId);
    }
}