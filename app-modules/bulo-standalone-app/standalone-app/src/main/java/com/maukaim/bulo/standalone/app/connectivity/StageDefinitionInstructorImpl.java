package com.maukaim.bulo.standalone.app.connectivity;

import com.maukaim.bulo.definitions.data.definition.technical.TechnicalStageDefinition;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.standalone.data.lifecycle.StageDefinitionInstructor;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.StageDefinitionAdapter;

import static com.maukaim.bulo.standalone.data.lifecycle.runs.StandaloneUtils.EXECUTOR_ID;

public class StageDefinitionInstructorImpl implements StageDefinitionInstructor {
    private final StageDefinitionService definitionService;
    private final StageDefinitionAdapter definitionAdapter;

    public StageDefinitionInstructorImpl(StageDefinitionService definitionService, StageDefinitionAdapter definitionAdapter) {
        this.definitionService = definitionService;
        this.definitionAdapter = definitionAdapter;
    }

    @Override
    public void create(com.maukaim.bulo.executors.data.models.StageDefinition definition) {
        TechnicalStageDefinition technicalStageDefinition = this.definitionAdapter.adapteFromExecutorModule(definition);
        this.definitionService.register(EXECUTOR_ID, technicalStageDefinition);
    }

    @Override
    public void remove(String stageDefinitionId) {
        this.definitionService.unregister(EXECUTOR_ID, stageDefinitionId);
    }
}
