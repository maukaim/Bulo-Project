package com.maukaim.bulo.runs.orchestrators.io.models.flowrun;

import java.util.Set;

public class ExecutionGraphDto {
    private final Set<FlowRunStageDto> flowRunStages;

    public ExecutionGraphDto(Set<FlowRunStageDto> flowRunStages) {
        this.flowRunStages = flowRunStages;
    }

    public Set<FlowRunStageDto> getFlowRunStages() {
        return flowRunStages;
    }

    @Override
    public String toString() {
        return "ExecutionGraphDto{" +
                "flowRunStages=" + flowRunStages +
                '}';
    }
}
