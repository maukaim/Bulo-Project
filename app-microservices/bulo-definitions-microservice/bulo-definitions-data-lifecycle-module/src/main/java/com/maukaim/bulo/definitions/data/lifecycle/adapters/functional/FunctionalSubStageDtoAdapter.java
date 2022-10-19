package com.maukaim.bulo.definitions.data.lifecycle.adapters.functional;

import com.maukaim.bulo.commons.io.instructions.models.functional.FsStageDto;
import com.maukaim.bulo.definitions.data.functional.FsStage;

public interface FunctionalSubStageAdapter {
    FsStageDto adapte(FsStage fsStage);
}
