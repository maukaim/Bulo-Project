package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.StageRunAncestorDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.flowrun.StageRunAncestorDto;

import java.util.Set;

public class StageRunAncestorDtoAdapterImpl implements StageRunAncestorDtoAdapter {
    @Override
    public StageRunAncestorDto adapte(StageRunAncestor stageRunAncestor) {
        return stageRunAncestor == null ? null : new StageRunAncestorDto(
                stageRunAncestor.getFlowStageId(),
                resolve(stageRunAncestor.getOutputIds())
        );
    }

    private Set<String> resolve(Set<String> outputIds) {
        return outputIds == null ? Set.of() : outputIds;
    }
}
