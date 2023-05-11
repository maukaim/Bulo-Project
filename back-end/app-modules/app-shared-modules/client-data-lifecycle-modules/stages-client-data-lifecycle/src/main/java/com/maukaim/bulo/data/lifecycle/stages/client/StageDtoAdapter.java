package com.maukaim.bulo.data.lifecycle.stages.client;

import com.maukaim.bulo.io.stages.client.model.StageDto;
import com.maukaim.bulo.stages.models.stage.Stage;

public interface StageDtoAdapter {
    StageDto adapte(Stage stage);
}
