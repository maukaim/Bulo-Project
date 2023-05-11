package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.io.runs.orchestrators.system.models.stagerun.StageRunDependencyDto;

public interface StageRunDependencyDtoAdapter {
    StageRunDependencyDto adapte(RunDependency dependency);
}
