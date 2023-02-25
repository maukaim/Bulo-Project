package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.io.flows.client.model.FlowStageDto;
import com.maukaim.bulo.io.flows.client.model.InputDependencyDto;
import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.FlowStageAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.InputDependencyAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowStageAdapterImpl implements FlowStageAdapter {
    private final InputDependencyAdapter inputDependencyAdapter;

    public FlowStageAdapterImpl(InputDependencyAdapter inputDependencyAdapter) {
        this.inputDependencyAdapter = inputDependencyAdapter;
    }

    @Override
    public FlowStage adapte(FlowStageDto dto) {
        return new FlowStage(
                dto.getFlowStageId(),
                resolve(dto.getIoDependencies())
        );
    }

    private Set<InputDependency> resolve(Set<InputDependencyDto> ioDependencies) {
        return ioDependencies == null ? null : ioDependencies.stream()
                .map(this.inputDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
