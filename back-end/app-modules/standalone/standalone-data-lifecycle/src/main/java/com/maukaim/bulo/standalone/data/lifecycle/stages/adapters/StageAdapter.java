package com.maukaim.bulo.standalone.data.lifecycle.stages.adapters;

import com.maukaim.bulo.stages.models.stage.Stage;

public interface StageAdapter {
    com.maukaim.bulo.flows.data.models.stage.Stage adapteFlowModule(Stage stage);
    com.maukaim.bulo.executors.data.stages.Stage adapteExecutorModule(Stage stage);
    com.maukaim.bulo.definitions.data.stage.Stage adapteDefinitionModule(Stage stage);
}
