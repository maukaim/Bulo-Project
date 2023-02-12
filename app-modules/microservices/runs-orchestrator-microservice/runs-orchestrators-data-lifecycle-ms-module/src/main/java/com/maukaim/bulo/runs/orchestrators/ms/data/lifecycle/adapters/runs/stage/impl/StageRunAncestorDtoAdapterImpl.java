package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.impl;

import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage.StageRunAncestorDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunAncestorDto;

public class StageRunAncestorDtoAdapterImpl implements StageRunAncestorDtoAdapter {
    @Override
    public StageRunAncestorDto adapte(StageRunAncestor ancestor) {
        return new StageRunAncestorDto(
                ancestor.getStageRunId(),
                ancestor.getOutputIds()
        );
    }
}
