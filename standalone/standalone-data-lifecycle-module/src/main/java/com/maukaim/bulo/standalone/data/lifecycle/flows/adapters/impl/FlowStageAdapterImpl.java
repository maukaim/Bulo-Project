package com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.impl;

import com.maukaim.bulo.flows.data.models.flow.FlowStage;
import com.maukaim.bulo.flows.data.models.flow.IoDependency;
import com.maukaim.bulo.runs.orchestrators.data.flow.InputDependency;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.FlowStageAdapter;
import com.maukaim.bulo.standalone.data.lifecycle.flows.adapters.InputDependencyAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class FlowStageAdapterImpl implements FlowStageAdapter {
    private final InputDependencyAdapter inputDependencyAdapter;

    public FlowStageAdapterImpl(InputDependencyAdapter inputDependencyAdapter) {
        this.inputDependencyAdapter = inputDependencyAdapter;
    }

    public com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage adapte(FlowStage flowStage){
        return flowStage == null ? null :  new com.maukaim.bulo.runs.orchestrators.data.flow.FlowStage(
                flowStage.getFlowStageId(),
                resolveInputDependencies(flowStage.getIoDependencies())
        );
    }

    private Set<InputDependency> resolveInputDependencies(Set<IoDependency> ioDependencies) {
        return ioDependencies == null ? Set.of() : ioDependencies.stream()
                .map(inputDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
