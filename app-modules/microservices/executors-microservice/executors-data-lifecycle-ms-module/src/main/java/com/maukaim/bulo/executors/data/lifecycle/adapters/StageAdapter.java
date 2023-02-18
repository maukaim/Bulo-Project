package com.maukaim.bulo.executors.data.lifecycle.adapters;

import com.maukaim.bulo.executors.data.stages.Stage;
import com.maukaim.bulo.io.stages.client.model.TechnicalStageDto;

public interface StageAdapter {
    Stage adapte(TechnicalStageDto stageDto);
}
