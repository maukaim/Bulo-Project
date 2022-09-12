package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.stage;

import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.io.models.StageRunDto;

public interface StageRunAdapter {
    StageRun adapte(StageRunDto dto);
}
