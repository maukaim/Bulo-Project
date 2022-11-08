package com.maukaim.bulo.standalone.data.lifecycle.stages.sub.stores;

import com.maukaim.bulo.commons.models.StageType;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;
import com.maukaim.bulo.stages.models.stage.Stage;
import com.maukaim.bulo.standalone.data.lifecycle.UnsupportedDataMethodException;
import com.maukaim.bulo.standalone.data.lifecycle.stages.MainStageStore;

public class OrchestratorModuleFunctionalStageStore implements FunctionalStageStore {
    private final MainStageStore mainStageStore;

    public OrchestratorModuleFunctionalStageStore(MainStageStore mainStageStore) {
        this.mainStageStore = mainStageStore;
    }

    @Override
    public String getDefinitionId(String id) {
        Stage stage = this.mainStageStore.getById(id);
        if(stage.getStageType() == StageType.FUNCTIONAL){
            return stage.getDefinitionId();
        }else{
            throw new UnsupportedDataMethodException("The retrieve stage is not FUNCTIONAL -> " + stage);
        }
    }

    @Override
    public void put(String functionalStageId, String definitionId) {
        throw UnsupportedDataMethodException.isSubStore();
    }

    @Override
    public String remove(String functionalStageId) {
        throw UnsupportedDataMethodException.isSubStore();
    }
}
