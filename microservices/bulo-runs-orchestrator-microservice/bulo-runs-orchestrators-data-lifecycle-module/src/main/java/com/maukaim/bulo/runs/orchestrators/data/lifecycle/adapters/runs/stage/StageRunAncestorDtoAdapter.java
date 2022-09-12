package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunAncestorDto;

public interface StageRunAncestorDtoAdapter {
    StageRunAncestorDto adapte(StageRunAncestor ancestor);
}
