package com.maukaim.bulo.flows.ms.data.lifecycle.adapters;

import com.maukaim.bulo.flows.data.models.stage.Stage;
import com.maukaim.bulo.io.flows.stage.StageDto;

public interface StageAdapter {
    Stage adapte(StageDto stage);
}
