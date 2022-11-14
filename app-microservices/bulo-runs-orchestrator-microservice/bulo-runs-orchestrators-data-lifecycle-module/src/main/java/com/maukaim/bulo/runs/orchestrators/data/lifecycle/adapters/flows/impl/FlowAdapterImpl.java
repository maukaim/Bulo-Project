package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.impl;

import com.maukaim.bulo.runs.orchestrators.data.flow.FailureAlternativeRoute;
import com.maukaim.bulo.runs.orchestrators.data.flow.Flow;
import com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.FailureAlternativeRouteAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.FlowAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.FlowStageAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.OwnerKeyAdapter;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FailureAlternativeRoutesDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.FlowStageDto;
import com.maukaim.bulo.runs.orchestrators.io.models.flow.OwnerKeyDto;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowAdapterImpl implements FlowAdapter {
    private final OwnerKeyAdapter ownerKeyAdapter;
    private final FlowStageAdapter flowStageAdapter;
    private final FailureAlternativeRouteAdapter failureAlternativeRouteAdapter;

    public FlowAdapterImpl(OwnerKeyAdapter ownerKeyAdapter,
                           FlowStageAdapter flowStageAdapter,
                           FailureAlternativeRouteAdapter failureAlternativeRouteAdapter) {
        this.ownerKeyAdapter = ownerKeyAdapter;
        this.flowStageAdapter = flowStageAdapter;
        this.failureAlternativeRouteAdapter = failureAlternativeRouteAdapter;
    }

    @Override
    public Flow adapte(FlowDto dto) {
        return new Flow(
                dto.getFlowId(),
                resolveOwnerKeys(dto.getOwnerKeys()),
                resolveFlowStages(dto.getFlowStages()),
                dto.isParallelRunAllowed(),
                resolveFailureAlternatives(dto.getFailureAlternativeRoutes()));
    }

    private Set<FailureAlternativeRoute> resolveFailureAlternatives(Set<FailureAlternativeRoutesDto> failureAlternativeRoutes) {
        return failureAlternativeRoutes == null ? Set.of() : failureAlternativeRoutes.stream()
                .map(this.failureAlternativeRouteAdapter::adapte)
                .collect(Collectors.toSet());
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
