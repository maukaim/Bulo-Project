package com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.stages;


import com.maukaim.bulo.definitions.data.stage.Stage;
import com.maukaim.bulo.io.definitions.system.stage.StageDto;

public interface StageAdapter {
    Stage adapte(StageDto stage);
}
