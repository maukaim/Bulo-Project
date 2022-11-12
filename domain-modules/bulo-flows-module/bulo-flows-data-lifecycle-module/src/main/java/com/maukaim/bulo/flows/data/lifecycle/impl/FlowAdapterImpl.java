package com.maukaim.bulo.flows.data.lifecycle.impl;

import com.maukaim.bulo.flows.data.lifecycle.FailureAlternativeRouteAdapter;
import com.maukaim.bulo.flows.data.lifecycle.FlowAdapter;
import com.maukaim.bulo.flows.data.lifecycle.FlowStageAdapter;
import com.maukaim.bulo.flows.data.lifecycle.OwnerKeyAdapter;
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

public class FlowAdapterImpl implements FlowAdapter {
    private final OwnerKeyAdapter ownerKeyAdapter;
    private final FlowStageAdapter flowStageAdapter;
    private final FailureAlternativeRouteAdapter failureAlternativeRouteAdapter;

    public FlowAdapterImpl(OwnerKeyAdapter ownerKeyAdapter, FlowStageAdapter flowStageAdapter,
                           FailureAlternativeRouteAdapter failureAlternativeRouteAdapter) {
        this.failureAlternativeRouteAdapter = failureAlternativeRouteAdapter;
        this.ownerKeyAdapter = ownerKeyAdapter;
        this.flowStageAdapter = flowStageAdapter;
    }

    @Override
    public Flow adapte(FlowDto dto) {
        return new Flow(
                dto.getFlowId(),
                resolveOwnerKeys(dto.getOwnerKeys()),
                resolveFlowStages(dto.getFlowStages()),
                resolveFailureAlternatives(dto.getFailureAlternativeRoutes()),
                dto.isParallelRunAllowed()
        );
    }

    private Set<FailureAlternativeRoute> resolveFailureAlternatives(Set<FailureAlternativeRoutesDto> failureAlternativeRoutes) {
        return failureAlternativeRoutes == null ? Set.of() : failureAlternativeRoutes.stream()
                .map(this.failureAlternativeRouteAdapter::adapte)
                .collect(Collectors.toSet());
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
