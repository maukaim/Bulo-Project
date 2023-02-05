package com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters;

import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;

public interface StageDefinitionAdapter {
    com.maukaim.bulo.stages.models.definition.StageDefinition adapteStageModule(StageDefinition definition);
    com.maukaim.bulo.flows.data.models.definition.StageDefinition adapteFlowModule(StageDefinition definition);
    com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition adapteOrchestratorModule(FunctionalStageDefinition definition);
    TechnicalStageDefinition adapteFromExecutorModule(com.maukaim.bulo.runners.api.models.StageDefinition stageDefinition);
}
