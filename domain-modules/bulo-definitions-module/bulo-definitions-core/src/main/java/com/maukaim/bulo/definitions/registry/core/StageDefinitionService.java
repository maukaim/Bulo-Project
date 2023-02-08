package com.maukaim.bulo.definitions.registry.core;

import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition;
import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.report.StageDefinitionCreateReport;

import java.util.List;

public interface StageDefinitionService {
    StageDefinitionCreateReport register(FunctionalStageDefinition functionalStageDefinition);
    StageDefinitionCreateReport register(String stageExecutorId, TechnicalStageDefinition definition);
    void unregister(String stageExecutorId, String definitionId);

    List<StageDefinition> getAll();
}
