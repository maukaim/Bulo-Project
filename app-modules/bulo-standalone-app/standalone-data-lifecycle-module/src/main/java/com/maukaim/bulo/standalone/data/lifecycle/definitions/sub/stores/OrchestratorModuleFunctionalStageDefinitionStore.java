package com.maukaim.bulo.standalone.data.lifecycle.definitions.sub.stores;

import com.maukaim.bulo.definitions.data.definition.StageDefinition;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageDefinitionStore;
import com.maukaim.bulo.runs.orchestrators.data.definition.FunctionalStageDefinition;
import com.maukaim.bulo.standalone.data.lifecycle.UnsupportedDataMethodException;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.MainDefinitionStore;
import com.maukaim.bulo.standalone.data.lifecycle.definitions.adapters.StageDefinitionAdapter;

public class OrchestratorModuleFunctionalStageDefinitionStore implements FunctionalStageDefinitionStore {
    private final MainDefinitionStore mainDefinitionStore;
    private final StageDefinitionAdapter stageDefinitionAdapter;

    public OrchestratorModuleFunctionalStageDefinitionStore(MainDefinitionStore mainDefinitionStore, StageDefinitionAdapter stageDefinitionAdapter) {
        this.mainDefinitionStore = mainDefinitionStore;
        this.stageDefinitionAdapter = stageDefinitionAdapter;
    }

    @Override
    public FunctionalStageDefinition getById(String definitionId) {
        StageDefinition definition = this.mainDefinitionStore.getById(definitionId);
        return switch (definition.getStageDefinitionType()) {
            case FUNCTIONAL -> this.stageDefinitionAdapter.adapteOrchestratorModule((com.maukaim.bulo.definitions.data.definition.functional.FunctionalStageDefinition) definition);
            case TECHNICAL -> throw new UnsupportedDataMethodException("Technical Stage not supported");
        };
    }

    @Override
    public void put(FunctionalStageDefinition definition) {
        throw UnsupportedDataMethodException.isSubStore();
    }

    @Override
    public void remove(String definitionId) {
        throw UnsupportedDataMethodException.isSubStore();
    }
}
