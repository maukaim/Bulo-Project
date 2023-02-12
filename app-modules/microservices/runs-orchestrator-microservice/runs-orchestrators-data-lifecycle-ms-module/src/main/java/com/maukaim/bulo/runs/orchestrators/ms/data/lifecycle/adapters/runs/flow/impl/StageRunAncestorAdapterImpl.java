package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.StageRunAncestorAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.ContextStageAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.StageRunAncestorDto;

import java.util.Set;

public class StageRunAncestorAdapterImpl implements StageRunAncestorAdapter {
    @Override
    public ContextStageAncestor adapte(StageRunAncestorDto dto) {
        return dto == null ? null : new ContextStageAncestor(dto.getFlowStageId(), Set.copyOf(dto.getOutputIds()));
    }
}
