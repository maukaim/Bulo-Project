package com.maukaim.bulo.flows.core;

import com.maukaim.bulo.flows.data.models.stage.Stage;

public interface StageService {
    Stage getById(String stageId);
}
