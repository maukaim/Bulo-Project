package com.maukaim.bulo.executors.data.lifecycle.adapters.impl;

import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunAncestorAdapter;
import com.maukaim.bulo.executors.data.runs.StageRunAncestor;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunAncestorDto;

public class StageRunAncestorAdapterImpl implements StageRunAncestorAdapter {
    @Override
    public StageRunAncestor adapte(StageRunAncestorDto dto) {
        return new StageRunAncestor(
                dto.getStageRunId(),
                dto.getOutputIds());
    }
}
