package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.io.runs.orchestrators.models.stagerun.StageRunAncestorDto;

public interface StageRunAncestorDtoAdapter {
    StageRunAncestorDto adapte(StageRunAncestor ancestor);
}
