package com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun;

import com.maukaim.bulo.commons.models.ContextStageId;

import java.util.Objects;
import java.util.Set;

public class FlowRunStageDto {
    private final ContextStageId contextStageId;
    private final Set<FlowStageDependencyDto> flowStageDependencies;

    public FlowRunStageDto(ContextStageId contextStageId, Set<FlowStageDependencyDto> flowStageDependencies) {
        this.contextStageId = contextStageId;
        this.flowStageDependencies = flowStageDependencies;
    }

    public ContextStageId getFlowStageId() {
        return contextStageId;
    }

    public Set<FlowStageDependencyDto> getFlowStageDependencies() {
        return flowStageDependencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowRunStageDto that = (FlowRunStageDto) o;
        return contextStageId.equals(that.contextStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextStageId);
    }

    @Override
    public String toString() {
        return "FlowRunStageDto{" +
                "flowStageId=" + contextStageId +
                ", flowStageDependencies=" + flowStageDependencies +
                '}';
    }
}
