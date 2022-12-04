package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.runs.orchestrators.io.models.stagerun.StageRunDependencyDto;

public interface StageRunDependencyDtoAdapter {
    StageRunDependencyDto adapte(RunDependency dependency);
}
