package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.ExecutionGraphDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowStageDependencyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowRunStageDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageDependencyDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExecutionGraphDtoAdapterImpl implements ExecutionGraphDtoAdapter {
    private final FlowStageDependencyDtoAdapter stageRunDependencyAdapter;

    public ExecutionGraphDtoAdapterImpl(FlowStageDependencyDtoAdapter flowStageDependencyDtoAdapter) {
        this.stageRunDependencyAdapter = flowStageDependencyDtoAdapter;
    }

    @Override
    public ExecutionGraphDto adapte(ExecutionGraph executionGraph) {
        return new ExecutionGraphDto(executionGraph == null ? Set.of() : resolveFlowRunStages(executionGraph.toDependencyMap()));
    }

    private Set<FlowRunStageDto> resolveFlowRunStages(Map<ContextualizedStageId, Set<FlowStageDependency>> dependencyMap) {
        return dependencyMap == null ? Set.of() : dependencyMap.entrySet().stream()
                .map(entry -> new FlowRunStageDto(entry.getKey(), resolve(entry.getValue())))
                .collect(Collectors.toSet());
    }

    private Set<FlowStageDependencyDto> resolve(Set<FlowStageDependency> stageRunDependencies) {
        return stageRunDependencies == null ? Set.of() : stageRunDependencies.stream()
                .map(this.stageRunDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
