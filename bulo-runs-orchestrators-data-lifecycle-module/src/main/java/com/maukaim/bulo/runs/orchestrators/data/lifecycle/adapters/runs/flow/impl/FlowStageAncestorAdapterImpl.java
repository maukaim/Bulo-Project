package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowStageAncestorAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowStageAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.FlowStageAncestorDto;

import java.util.Set;

public class FlowStageAncestorAdapterImpl implements FlowStageAncestorAdapter {
    @Override
    public FlowStageAncestor adapte(FlowStageAncestorDto dto) {
        return new FlowStageAncestor(dto.getFlowStageId(), Set.copyOf(dto.getOutputIds()));
    }
}
