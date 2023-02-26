package com.maukaim.bulo.data.lifecycle.flows.client.impl;

import com.maukaim.bulo.data.lifecycle.flows.client.FlowAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.OwnerKeyAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.FlowStageAdapter;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.io.flows.client.model.FlowDto;
import com.maukaim.bulo.io.flows.client.model.FlowStageDto;
import com.maukaim.bulo.io.flows.client.model.OwnerKeyDto;

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
