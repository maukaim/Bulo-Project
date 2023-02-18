package com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Objects;
import java.util.Set;

public class StageRunAncestorDto {
    private ContextStageId contextStageId;
    private Set<String> outputIds;

    public StageRunAncestorDto(ContextStageId contextStageId, Set<String> outputIds) {
        this.contextStageId = contextStageId;
        this.outputIds = outputIds;
    }

    public ContextStageId getFlowStageId() {
        return contextStageId;
    }

    public Set<String> getOutputIds() {
        return outputIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageRunAncestorDto that = (StageRunAncestorDto) o;
        return contextStageId.equals(that.contextStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextStageId);
    }

    @Override
    public String toString() {
        return "FlowStageAncestorDto{" +
                "flowStageId=" + contextStageId +
                ", outputIds=" + outputIds +
                '}';
    }
}
