package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage.StageRunAncestorAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunAncestorDto;

public class StageRunAncestorAdapterImpl implements StageRunAncestorAdapter {
    @Override
    public StageRunAncestor adapte(StageRunAncestorDto dto) {
        return new StageRunAncestor(
                dto.getStageRunId(),
                dto.getOutputIds());
    }
}
