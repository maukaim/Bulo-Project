package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.runs.StageRunAncestor;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunAncestorDto;

public interface StageRunAncestorAdapter {
    StageRunAncestor adapte(StageRunAncestorDto dto);
}
