package com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters;

import com.maukaim.bulo.runs.orchestrators.data.models.StageRun;
import com.maukaim.bulo.runs.orchestrators.io.both.model.StageRunDto;

public interface StageRunDtoAdapter {
    StageRunDto adapte(StageRun stageRun);
}
