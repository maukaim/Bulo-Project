package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;

import com.maukaim.bulo.commons.models.ContextualizedStageId;

import java.util.Objects;
import java.util.Set;

public class FlowRunStageDto {
    private final ContextualizedStageId contextualizedStageId;
    private final Set<FlowStageDependencyDto> flowStageDependencies;

    public FlowRunStageDto(ContextualizedStageId contextualizedStageId, Set<FlowStageDependencyDto> flowStageDependencies) {
        this.contextualizedStageId = contextualizedStageId;
        this.flowStageDependencies = flowStageDependencies;
    }

    public ContextualizedStageId getFlowStageId() {
        return contextualizedStageId;
    }

    public Set<FlowStageDependencyDto> getFlowStageDependencies() {
        return flowStageDependencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowRunStageDto that = (FlowRunStageDto) o;
        return contextualizedStageId.equals(that.contextualizedStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextualizedStageId);
    }

    @Override
    public String toString() {
        return "FlowRunStageDto{" +
                "flowStageId=" + contextualizedStageId +
                ", flowStageDependencies=" + flowStageDependencies +
                '}';
    }
}
