package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.StageRunAncestorDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowStageDependencyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextStageAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.StageRunAncestorDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowStageDependencyDtoAdapterImpl implements FlowStageDependencyDtoAdapter {
    private final StageRunAncestorDtoAdapter stageRunAncestorDtoAdapter;

    public FlowStageDependencyDtoAdapterImpl(StageRunAncestorDtoAdapter stageRunAncestorDtoAdapter) {
        this.stageRunAncestorDtoAdapter = stageRunAncestorDtoAdapter;
    }

    @Override
    public FlowStageDependencyDto adapte(ContextualizedStageDependency contextualizedStageDependency) {
        return contextualizedStageDependency == null ? null : new FlowStageDependencyDto(
                contextualizedStageDependency.getInputId(),
                resolve(contextualizedStageDependency.getAncestors())
        );
    }

    private Set<StageRunAncestorDto> resolve(Set<ContextStageAncestor> contextStageAncestors) {
        return contextStageAncestors == null ? Set.of() : contextStageAncestors.stream()
                .map(this.stageRunAncestorDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
