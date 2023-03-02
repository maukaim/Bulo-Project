package com.maukaim.bulo.definitions.ms.data.lifecycle.adapters;


import com.maukaim.bulo.definitions.data.stage.Stage;
import com.maukaim.bulo.io.stages.client.model.StageDto;

public interface StageAdapter {
    Stage adapte(StageDto stage);
}
