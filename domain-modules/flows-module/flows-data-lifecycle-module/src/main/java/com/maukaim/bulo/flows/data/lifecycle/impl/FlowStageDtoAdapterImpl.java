package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.FlowStageDtoAdapter;
import com.maukaim.bulo.flows.data.lifecycle.IoDependencyDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.io.flows.client.model.FlowStageDto;
import com.maukaim.bulo.io.flows.client.model.InputDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowStageDtoAdapterImpl implements FlowStageDtoAdapter {
    private final IoDependencyDtoAdapter ioDependencyDtoAdapter;

    public FlowStageDtoAdapterImpl(IoDependencyDtoAdapter ioDependencyDtoAdapter) {
        this.ioDependencyDtoAdapter = ioDependencyDtoAdapter;
    }

    @Override
    public FlowStageDto adapte(FlowStage flowStage) {
        return new FlowStageDto(
                flowStage.getFlowStageId(),
                resolveDependencies(flowStage.getIoDependencies())
        );
    }

    private Set<InputDependencyDto> resolveDependencies(Set<IoDependency> ioDependencies) {
        return ioDependencies == null ? null : ioDependencies.stream()
                .map(this.ioDependencyDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }

}
