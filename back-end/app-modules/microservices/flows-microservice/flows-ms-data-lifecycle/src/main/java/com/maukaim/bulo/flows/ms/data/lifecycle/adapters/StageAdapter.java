package com.maukaim.bulo.flows.ms.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.stage.Stage;
import com.maukaim.bulo.io.stages.client.model.StageDto;

public interface StageAdapter {
    Stage adapte(StageDto stage);
}
