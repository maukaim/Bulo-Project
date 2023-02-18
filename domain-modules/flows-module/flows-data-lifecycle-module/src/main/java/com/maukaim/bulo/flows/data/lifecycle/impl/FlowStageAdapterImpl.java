package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.FlowStageAdapter;
import com.maukaim.bulo.flows.data.lifecycle.IoDependencyAdapter;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.io.flows.client.model.FlowStageDto;
import com.maukaim.bulo.io.flows.client.model.IoDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowStageAdapterImpl implements FlowStageAdapter {
    private final IoDependencyAdapter ioDependencyAdapter;

    public FlowStageAdapterImpl(IoDependencyAdapter ioDependencyAdapter) {
        this.ioDependencyAdapter = ioDependencyAdapter;
    }

    @Override
    public FlowStage adapte(FlowStageDto dto) {
        return new FlowStage(
                dto.getFlowStageId(),
                resolveDependencies(dto.getIoDependencies())
        );
    }

    private Set<IoDependency> resolveDependencies(Set<IoDependencyDto> ioDependencies) {
        return ioDependencies == null ? null : ioDependencies.stream()
                .map(this.ioDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
