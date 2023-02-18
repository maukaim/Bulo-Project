package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.FlowAdapter;
import com.maukaim.bulo.flows.data.lifecycle.FlowStageAdapter;
import com.maukaim.bulo.flows.data.lifecycle.OwnerKeyAdapter;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.io.flows.flow.FlowDto;
import com.maukaim.bulo.io.flows.flow.FlowStageDto;
import com.maukaim.bulo.io.flows.flow.OwnerKeyDto;

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

    private Set<FlowStage> resolveFlowStages(Set<FlowStageDto> flowStages) {
        return flowStages == null ? null : flowStages.stream()
                .map(this.flowStageAdapter::adapte)
                .collect(Collectors.toSet());
    }


    private Set<OwnerKey> resolveOwnerKeys(Set<OwnerKeyDto> flowOwnerKeys) {
        return flowOwnerKeys == null ? null : flowOwnerKeys.stream()
                .map(this.ownerKeyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
