package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.ExecutionGraphAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.FlowStageDependencyAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowRunStageDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowStageDependencyDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExecutionGraphAdapterImpl implements ExecutionGraphAdapter {
    private final FlowStageDependencyAdapter flowStageDependencyAdapter;

    public ExecutionGraphAdapterImpl(FlowStageDependencyAdapter flowStageDependencyAdapter) {
        this.flowStageDependencyAdapter = flowStageDependencyAdapter;
    }

    @Override
    public ExecutionGraph adapte(ExecutionGraphDto dto) {
        return new ExecutionGraph(dto == null ? Map.of() : resolveFlowRunStages(dto.getFlowRunStages()));
    }

    private Set<ContextualizedStageDependency> resolveFlowStageDependencies(Set<FlowStageDependencyDto> dependencyDtos) {
        return dependencyDtos == null ? Set.of() : dependencyDtos.stream()
                .map(this.flowStageDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private Map<ContextStageId, Set<ContextualizedStageDependency>> resolveFlowRunStages(Set<FlowRunStageDto> flowRunStages) {
        return flowRunStages == null ? Map.of() : flowRunStages.stream()
                .collect(Collectors.toMap(
                        FlowRunStageDto::getFlowStageId,
                        flowRunStageDto -> this.resolveFlowStageDependencies(flowRunStageDto.getFlowStageDependencies())
                ));
    }
}
