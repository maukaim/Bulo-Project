package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.FlowAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.FlowStageAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.OwnerKeyAdapter;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.FlowDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.FlowStageDto;
import com.maukaim.bulo.io.runs.orchestrators.models.flow.OwnerKeyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowAdapterImpl implements FlowAdapter {
    private final OwnerKeyAdapter ownerKeyAdapter;
    private final FlowStageAdapter flowStageAdapter;

    public FlowAdapterImpl(OwnerKeyAdapter ownerKeyAdapter, FlowStageAdapter flowStageAdapter) {
        this.ownerKeyAdapter = ownerKeyAdapter;
        this.flowStageAdapter = flowStageAdapter;
    }

    @Override
    public Flow adapte(FlowDto dto) {
        return new Flow(
                dto.getFlowId(),
                resolveOwnerKeys(dto.getOwnerKeys()),
                resolveFlowStages(dto.getFlowStages()),
                dto.isParallelRunAllowed()
        );
    }

    private Set<FlowStage> resolveFlowStages(Set<FlowStageDto> flowStageDtos) {
        return flowStageDtos == null ? null : flowStageDtos.stream()
                .map(this.flowStageAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private Set<OwnerKey> resolveOwnerKeys(Set<OwnerKeyDto> ownerKeyDtos) {
        return ownerKeyDtos == null ? null : ownerKeyDtos.stream()
                .map(this.ownerKeyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
