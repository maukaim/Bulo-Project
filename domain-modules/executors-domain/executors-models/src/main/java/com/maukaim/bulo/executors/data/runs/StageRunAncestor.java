package com.maukaim.bulo.executors.data.runs;

import java.util.Objects;
import java.util.Set;

public class StageRunAncestor {
    private String stageRunId;
    private Set<String> outputIds;

    public StageRunAncestor(String stageRunId, Set<String> outputIds) {
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
        StageRunAncestor that = (StageRunAncestor) o;
        return stageRunId.equals(that.stageRunId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stageRunId);
    }

    @Override
    public String toString() {
        return "InputProvider{" +
                "flowStageId=" + stageRunId +
                ", outputIds=" + outputIds +
                '}';
    }
}
