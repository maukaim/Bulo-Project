package com.maukaim.bulo.flows.data.lifecycle.adapters.impl;

import com.maukaim.bulo.flows.data.lifecycle.adapters.FlowStageAdapter;
import com.maukaim.bulo.flows.data.lifecycle.adapters.IoDependencyAdapter;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.flows.io.flow.FlowStageDto;
import com.maukaim.bulo.flows.io.flow.IoDependencyDto;

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
