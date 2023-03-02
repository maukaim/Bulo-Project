package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions;

import com.maukaim.bulo.io.definitions.client.dtos.functional.FsStageDto;
import com.maukaim.bulo.runs.orchestrators.data.definition.FsStage;

public interface FunctionalSubStageAdapter {
    FsStage adapte(FsStageDto dto);
}
