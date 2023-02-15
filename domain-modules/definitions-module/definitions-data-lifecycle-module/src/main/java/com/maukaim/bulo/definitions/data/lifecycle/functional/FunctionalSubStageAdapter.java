package com.maukaim.bulo.definitions.data.lifecycle.functional;

import com.maukaim.bulo.io.definitions.shared.instructions.models.functional.FsStageDto;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;

public interface FunctionalSubStageAdapter {
    FsStage adapte(FsStageDto dto);
}
