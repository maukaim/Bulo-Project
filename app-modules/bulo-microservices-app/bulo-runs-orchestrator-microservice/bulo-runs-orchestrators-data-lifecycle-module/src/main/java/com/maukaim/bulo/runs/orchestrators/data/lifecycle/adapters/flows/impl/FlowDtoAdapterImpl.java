package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.FlowDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.FlowStageDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.OwnerKeyDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowStageDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.OwnerKeyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowDtoAdapterImpl implements FlowDtoAdapter {
    private final FlowStageDtoAdapter flowStageDtoAdapter;
    private final OwnerKeyDtoAdapter ownerKeyDtoAdapter;

    public FlowDtoAdapterImpl(FlowStageDtoAdapter flowStageDtoAdapter, OwnerKeyDtoAdapter ownerKeyDtoAdapter) {
        this.flowStageDtoAdapter = flowStageDtoAdapter;
        this.ownerKeyDtoAdapter = ownerKeyDtoAdapter;
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

    private Set<OwnerKeyDto> resolveOwnerKeys(Set<OwnerKey> ownerKeys) {
        return ownerKeys == null ? null : ownerKeys.stream()
                .map(this.ownerKeyDtoAdapter::adapte).collect(Collectors.toSet());
    }
}
