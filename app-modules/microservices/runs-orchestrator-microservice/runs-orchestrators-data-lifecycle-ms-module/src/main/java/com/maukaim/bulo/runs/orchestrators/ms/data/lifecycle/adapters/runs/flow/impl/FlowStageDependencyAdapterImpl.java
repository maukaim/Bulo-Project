package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.StageRunAncestorAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.FlowStageDependencyAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextStageAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.StageRunAncestorDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowStageDependencyAdapterImpl implements FlowStageDependencyAdapter {
    private final StageRunAncestorAdapter stageRunAncestorAdapter;

    public FlowStageDependencyAdapterImpl(StageRunAncestorAdapter stageRunAncestorAdapter) {
        this.stageRunAncestorAdapter = stageRunAncestorAdapter;
    }

    @Override
    public ContextualizedStageDependency adapte(FlowStageDependencyDto dto) {
        return dto == null ? null : new ContextualizedStageDependency(
                dto.getInputId(),
                resolve(dto.getAncestors())
        );
    }

    private Set<ContextStageAncestor> resolve(Set<StageRunAncestorDto> stageRunAncestorDtos) {
        return stageRunAncestorDtos == null ? Set.of() : stageRunAncestorDtos.stream()
                .map(this.stageRunAncestorAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
