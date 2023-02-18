package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.FlowStageDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.InputDependencyDtoAdapter;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.FlowStageDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.InputDependencyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowStageDtoAdapterImpl implements FlowStageDtoAdapter {
    private final InputDependencyDtoAdapter inputDependencyDtoAdapter;

    public FlowStageDtoAdapterImpl(InputDependencyDtoAdapter inputDependencyDtoAdapter) {
        this.inputDependencyDtoAdapter = inputDependencyDtoAdapter;
    }

    @Override
    public FlowStageDto adapte(FlowStage flowStage) {
        return new FlowStageDto(
                flowStage.getFlowStageId(),
                resolve(flowStage.getIoDependencies())
        );
    }

    private Set<InputDependencyDto> resolve(Set<InputDependency> ioDependencies) {
        return ioDependencies == null ? null : ioDependencies.stream()
                .map(this.inputDependencyDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
