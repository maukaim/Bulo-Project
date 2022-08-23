package com.maukaim.bulo.stages.persistence.adapters;

import com.maukaim.bulo.io.stages.StageData;
import com.maukaim.bulo.stages.models.stage.Stage;

public interface StageDataAdapter {
    StageData adapte(Stage stage);
}
