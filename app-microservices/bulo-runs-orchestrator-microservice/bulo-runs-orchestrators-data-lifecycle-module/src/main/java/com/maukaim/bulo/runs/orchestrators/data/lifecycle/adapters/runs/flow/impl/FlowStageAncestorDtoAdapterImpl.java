package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowStageAncestorDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageAncestorDto;

import java.util.Set;

public class FlowStageAncestorDtoAdapterImpl implements FlowStageAncestorDtoAdapter {
    @Override
    public FlowStageAncestorDto adapte(FlowStageAncestor flowStageAncestor) {
        return flowStageAncestor == null ? null : new FlowStageAncestorDto(
                flowStageAncestor.getFlowStageId(),
                resolve(flowStageAncestor.getOutputIds())
        );
    }

    private Set<String> resolve(Set<String> outputIds) {
        return outputIds == null ? Set.of() : outputIds;
    }
}
