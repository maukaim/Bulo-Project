package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;

import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.Objects;
import java.util.Set;

public class FlowRunStageDto {
    private final FlowStageId flowStageId;
    private final Set<FlowStageDependencyDto> flowStageDependencies;

    public FlowRunStageDto(FlowStageId flowStageId, Set<FlowStageDependencyDto> flowStageDependencies) {
        this.flowStageId = flowStageId;
        this.flowStageDependencies = flowStageDependencies;
    }

    public FlowStageId getFlowStageId() {
        return flowStageId;
    }

    public Set<FlowStageDependencyDto> getFlowStageDependencies() {
        return flowStageDependencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowRunStageDto that = (FlowRunStageDto) o;
        return flowStageId.equals(that.flowStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowStageId);
    }

    @Override
    public String toString() {
        return "FlowRunStageDto{" +
                "flowStageId=" + flowStageId +
                ", flowStageDependencies=" + flowStageDependencies +
                '}';
    }
}
