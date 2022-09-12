package com.maukaim.bulo.flows.data.lifecycle.adapters;


import com.maukaim.bulo.flows.data.models.stage.Stage;
import com.maukaim.bulo.flows.io.stage.StageDto;

public interface StageAdapter {
    Stage adapte(StageDto stage);
}
