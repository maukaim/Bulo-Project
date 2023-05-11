package com.maukaim.bulo.data.lifecycle.definitions.client.functional;

import com.maukaim.bulo.io.definitions.client.dtos.functional.FsStageDto;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;

public interface FunctionalSubStageAdapter {
    FsStage adapte(FsStageDto dto);
}
