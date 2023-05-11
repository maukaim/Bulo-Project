package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.io.runs.orchestrators.system.models.StageRunDto;

public interface StageRunDtoAdapter {
    StageRunDto<?> adapte(StageRun<?> technicalStageRun);
}
