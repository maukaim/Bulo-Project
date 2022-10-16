package com.maukaim.bulo.stages.data.lifecycle;

import com.maukaim.bulo.stages.io.models.stages.StageDto;
import com.maukaim.bulo.stages.models.stage.Stage;

public interface StageDtoAdapter {
    StageDto adapte(Stage stage);
}
