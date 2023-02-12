package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunAncestor;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunAncestorDto;

public interface StageRunAncestorAdapter {
    StageRunAncestor adapte(StageRunAncestorDto dto);
}
