package com.maukaim.bulo.executors.io.in.model;

import java.util.Objects;
import java.util.Set;

public class StageRunAncestorDto {
    private String stageRunId;
    private Set<String> outputIds;

    public StageRunAncestorDto(String stageRunId, Set<String> outputIds) {
        this.stageRunId = stageRunId;
        this.outputIds = outputIds;
    }

    public String getStageRunId() {
        return stageRunId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageRunAncestorDto that = (StageRunAncestorDto) o;
        return stageRunId.equals(that.stageRunId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stageRunId);
    }

    @Override
    public String toString() {
        return "AncestorRunsDto{" +
                "stageRunId='" + stageRunId + '\'' +
                ", outputIds=" + outputIds +
                '}';
    }
}
