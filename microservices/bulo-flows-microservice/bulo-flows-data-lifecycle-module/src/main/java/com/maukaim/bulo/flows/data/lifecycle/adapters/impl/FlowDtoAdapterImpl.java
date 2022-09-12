package com.maukaim.bulo.flows.data.lifecycle.adapters.impl;

import com.maukaim.bulo.flows.data.lifecycle.adapters.FlowDtoAdapter;
import com.maukaim.bulo.flows.data.lifecycle.adapters.FlowStageDtoAdapter;
import com.maukaim.bulo.flows.data.lifecycle.adapters.OwnerKeyDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.flows.io.flow.FlowDto;
import com.maukaim.bulo.flows.io.flow.FlowStageDto;
import com.maukaim.bulo.flows.io.flow.OwnerKeyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowDtoAdapterImpl implements FlowDtoAdapter {
    private final OwnerKeyDtoAdapter ownerKeyDtoAdapter;
    private final FlowStageDtoAdapter flowStageDtoAdapter;

    public FlowDtoAdapterImpl(OwnerKeyDtoAdapter ownerKeyDtoAdapter, FlowStageDtoAdapter flowStageDtoAdapter) {
        this.ownerKeyDtoAdapter = ownerKeyDtoAdapter;
        this.flowStageDtoAdapter = flowStageDtoAdapter;
    }

    @Override
    public FlowDto adapte(Flow flow) {
        return new FlowDto(
                flow.getFlowId(),
                resolveOwnerKeys(flow.getOwnerKeys()),
                resolveFlowStages(flow.getFlowStages()),
                flow.isParallelRunAllowed()
        );
    }

    private Set<FlowStageDto> resolveFlowStages(Set<FlowStage> flowStages) {
        return flowStages == null ? null : flowStages.stream()
                .map(this.flowStageDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private Set<OwnerKeyDto> resolveOwnerKeys(Set<OwnerKey> flowOwnerKeys) {
        return flowOwnerKeys == null ? null : flowOwnerKeys.stream()
                .map(this.ownerKeyDtoAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
