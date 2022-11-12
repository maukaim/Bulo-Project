package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.FailureAlternativeRouteDtoAdapter;
import com.maukaim.bulo.flows.data.lifecycle.FlowDtoAdapter;
import com.maukaim.bulo.flows.data.lifecycle.FlowStageDtoAdapter;
import com.maukaim.bulo.flows.data.lifecycle.OwnerKeyDtoAdapter;
import com.maukaim.bulo.flows.data.models.flow.FailureAlternativeRoute;
import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.OwnerKey;
import com.maukaim.bulo.flows.io.flow.FailureAlternativeRoutesDto;
import com.maukaim.bulo.flows.io.flow.FlowDto;
import com.maukaim.bulo.flows.io.flow.FlowStageDto;
import com.maukaim.bulo.flows.io.flow.OwnerKeyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowDtoAdapterImpl implements FlowDtoAdapter {
    private final OwnerKeyDtoAdapter ownerKeyDtoAdapter;
    private final FlowStageDtoAdapter flowStageDtoAdapter;
    private final FailureAlternativeRouteDtoAdapter failureAlternativeRouteDtoAdapter;

    public FlowDtoAdapterImpl(OwnerKeyDtoAdapter ownerKeyDtoAdapter,
                              FlowStageDtoAdapter flowStageDtoAdapter,
                              FailureAlternativeRouteDtoAdapter failureAlternativeRouteDtoAdapter) {
        this.ownerKeyDtoAdapter = ownerKeyDtoAdapter;
        this.flowStageDtoAdapter = flowStageDtoAdapter;
        this.failureAlternativeRouteDtoAdapter = failureAlternativeRouteDtoAdapter;
    }

    @Override
    public FlowDto adapte(Flow flow) {
        return new FlowDto(
                flow.getFlowId(),
                resolveOwnerKeys(flow.getOwnerKeys()),
                resolveFlowStages(flow.getFlowStages()),
                resolveFailureAlternativeRoutes(flow.getFailureAlternativeRoutes()), flow.isParallelRunAllowed()
        );
    }

    private Set<FailureAlternativeRoutesDto> resolveFailureAlternativeRoutes(Set<FailureAlternativeRoute> failureAlternativeRoutes) {
        return failureAlternativeRoutes == null ? Set.of() : failureAlternativeRoutes.stream()
                .map(this.failureAlternativeRouteDtoAdapter::adapte)
                .collect(Collectors.toSet());
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
