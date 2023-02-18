package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions;

import com.maukaim.bulo.runs.orchestrators.data.definition.FsStage;
import com.maukaim.bulo.io.runs.orchestrators.system.models.definition.FsStageDto;

public interface FunctionalSubStageAdapter {
    FsStage adapte(FsStageDto dto);
}
