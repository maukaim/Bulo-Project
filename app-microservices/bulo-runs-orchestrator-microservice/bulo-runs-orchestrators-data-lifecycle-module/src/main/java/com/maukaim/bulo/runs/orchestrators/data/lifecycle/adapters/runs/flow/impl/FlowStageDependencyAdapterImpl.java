package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowStageAncestorAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowStageDependencyAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageAncestorDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowStageDependencyAdapterImpl implements FlowStageDependencyAdapter {
    private final FlowStageAncestorAdapter flowStageAncestorAdapter;

    public FlowStageDependencyAdapterImpl(FlowStageAncestorAdapter flowStageAncestorAdapter) {
        this.flowStageAncestorAdapter = flowStageAncestorAdapter;
    }

    @Override
    public ContextualizedStageDependency adapte(FlowStageDependencyDto dto) {
        return dto == null ? null : new ContextualizedStageDependency(
                dto.getInputId(),
                resolve(dto.getAncestors())
        );
    }

    private Set<FlowStageAncestor> resolve(Set<FlowStageAncestorDto> flowStageAncestorDtos) {
        return flowStageAncestorDtos == null ? Set.of() : flowStageAncestorDtos.stream()
                .map(this.flowStageAncestorAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
