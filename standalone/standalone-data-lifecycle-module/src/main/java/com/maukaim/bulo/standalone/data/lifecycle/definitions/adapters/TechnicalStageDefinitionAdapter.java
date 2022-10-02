package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;

public interface TechnicalStageDefinitionAdapter {
    com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition adapteStageModule(TechnicalStageDefinition definition);
    com.maukaim.bulo.flows.data.models.definition.StageDefinition adapteFlowModule(TechnicalStageDefinition definition);
    TechnicalStageDefinition adapteFromExecutorModule(com.maukaim.bulo.executors.data.models.StageDefinition stageDefinition);
}
