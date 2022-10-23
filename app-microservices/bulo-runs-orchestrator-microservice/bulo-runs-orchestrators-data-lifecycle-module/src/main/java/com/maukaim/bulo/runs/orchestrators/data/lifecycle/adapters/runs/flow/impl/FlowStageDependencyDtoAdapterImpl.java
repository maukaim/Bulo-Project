package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowStageAncestorDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowStageDependencyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageAncestor;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextualizedStageDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageAncestorDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowStageDependencyDtoAdapterImpl implements FlowStageDependencyDtoAdapter {
    private final FlowStageAncestorDtoAdapter flowStageAncestorDtoAdapter;

    public FlowStageDependencyDtoAdapterImpl(FlowStageAncestorDtoAdapter flowStageAncestorDtoAdapter) {
        this.flowStageAncestorDtoAdapter = flowStageAncestorDtoAdapter;
    }

    @Override
    public FlowStageDependencyDto adapte(ContextualizedStageDependency contextualizedStageDependency) {
        return contextualizedStageDependency == null ? null : new FlowStageDependencyDto(
                contextualizedStageDependency.getInputId(),
                resolve(contextualizedStageDependency.getAncestors())
        );
    }

    private Set<FlowStageAncestorDto> resolve(Set<FlowStageAncestor> flowStageAncestors) {
        return flowStageAncestors == null ? Set.of() : flowStageAncestors.stream()
                .map(this.flowStageAncestorDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
