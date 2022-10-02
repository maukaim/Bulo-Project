package com.maukaim.bulo.standalone.app.connectivity;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.executors.data.models.StageDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.StageDefinitionInstructor;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.TechnicalStageDefinitionAdapter;

import static com.maukaim.bulo.standalone.data.lifecycle.runs.StandaloneUtils.EXECUTOR_ID;

public class StageDefinitionInstructorImpl implements StageDefinitionInstructor {
    private final TechnicalStageDefinitionService definitionService;
    private final TechnicalStageDefinitionAdapter definitionAdapter;

    public StageDefinitionInstructorImpl(TechnicalStageDefinitionService definitionService, TechnicalStageDefinitionAdapter definitionAdapter) {
        this.definitionService = definitionService;
        this.definitionAdapter = definitionAdapter;
    }

    @Override
    public void create(StageDefinition definition) {
        TechnicalStageDefinition technicalStageDefinition = this.definitionAdapter.adapteFromExecutorModule(definition);
        this.definitionService.register(EXECUTOR_ID, technicalStageDefinition);
    }

    @Override
    public void remove(String stageDefinitionId) {
        this.definitionService.unregister(EXECUTOR_ID, stageDefinitionId);
    }
}
