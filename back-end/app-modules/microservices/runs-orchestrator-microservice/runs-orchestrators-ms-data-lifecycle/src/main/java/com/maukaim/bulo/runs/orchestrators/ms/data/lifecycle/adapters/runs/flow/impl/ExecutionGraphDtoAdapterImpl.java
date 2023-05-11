package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.ExecutionGraphDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.FlowStageDependencyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ExecutionGraph;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.ExecutionGraphDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowRunStageDto;
import com.maukaim.bulo.io.runs.orchestrators.system.models.flowrun.FlowStageDependencyDto;

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

    private Set<FlowRunStageDto> resolveFlowRunStages(Map<ContextStageId, Set<ContextualizedStageDependency>> dependencyMap) {
        return dependencyMap == null ? Set.of() : dependencyMap.entrySet().stream()
                .map(entry -> new FlowRunStageDto(entry.getKey(), resolve(entry.getValue())))
                .collect(Collectors.toSet());
    }

    private Set<FlowStageDependencyDto> resolve(Set<ContextualizedStageDependency> stageRunDependencies) {
        return stageRunDependencies == null ? Set.of() : stageRunDependencies.stream()
                .map(this.stageRunDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
