package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

public interface StageRunDependencyAdapter {
    StageRunDependency adapte(StageRunDependencyDto dto);
}
