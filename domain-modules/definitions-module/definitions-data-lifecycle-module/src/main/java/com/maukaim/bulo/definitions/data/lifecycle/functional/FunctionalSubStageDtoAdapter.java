package com.maukaim.bulo.definitions.data.lifecycle.functional;

import com.maukaim.bulo.commons.io.instructions.models.functional.FsStageDto;
import com.maukaim.bulo.definitions.data.definition.functional.FsStage;

public interface FunctionalSubStageDtoAdapter {
    FsStageDto adapte(FsStage fsStage);
}
