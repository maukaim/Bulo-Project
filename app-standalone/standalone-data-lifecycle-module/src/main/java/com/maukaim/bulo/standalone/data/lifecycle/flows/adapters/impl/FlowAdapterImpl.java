package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.impl;

import com.maukaim.bulo.flows.data.models.flow.Flow;
import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.runs.orchestrators.data.flow.FailureAlternativeRoute;
import com.maukaim.bulo.runs.orchestrators.data.flow.OwnerKey;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.FailureAlternativeRouteAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.FlowAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.FlowStageAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.OwnerKeyAdapter;

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

    public com.maukaim.bulo.runs.orchestrators.data.flow.Flow adapte(Flow flow) {
        return flow == null ? null : new com.maukaim.bulo.runs.orchestrators.data.flow.Flow(
                flow.getFlowId(),
                this.resolveOwnerKeys(flow.getOwnerKeys()),
                this.resolveFlowStages(flow.getFlowStages()),
                flow.isParallelRunAllowed(),
                this.resolveFailureAlternatives(flow.getFailureAlternativeRoutes()));
    }

    private Set<FailureAlternativeRoute> resolveFailureAlternatives(Set<com.maukaim.bulo.flows.data.models.flow.FailureAlternativeRoute> failureAlternativeRoutes) {
        return failureAlternativeRoutes == null ? Set.of() : failureAlternativeRoutes.stream()
                .map(this.failureAlternativeRouteAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private Set<com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage> resolveFlowStages(Set<FlowStage> flowStages) {
        return flowStages == null ? Set.of() : flowStages.stream()
                .map(flowStageAdapter::adapte)
                .collect(Collectors.toSet());
    }

    private Set<OwnerKey> resolveOwnerKeys(Set<com.maukaim.bulo.flows.data.models.flow.OwnerKey> ownerKeys) {
        return ownerKeys == null ? Set.of() : ownerKeys.stream()
                .map(ownerKeyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
