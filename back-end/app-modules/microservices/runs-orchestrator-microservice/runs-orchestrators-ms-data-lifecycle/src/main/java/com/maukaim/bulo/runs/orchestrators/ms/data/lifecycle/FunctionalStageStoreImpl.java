package com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle;

import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;

import java.util.HashMap;
import java.util.Map;

public class FunctionalStageStoreImpl implements FunctionalStageStore {
    private final Map<String, String> definitionIdByFunctionalStageId;

    public FunctionalStageStoreImpl(Map<String, String> initialCache) {
        this.definitionIdByFunctionalStageId = new HashMap<>(initialCache);
    }

    @Override
    public String getDefinitionId(String id) {
        return definitionIdByFunctionalStageId.get(id);
    }

    @Override
    public void put(String functionalStageId, String definitionId) {
        definitionIdByFunctionalStageId.put(functionalStageId, definitionId);
    }

    @Override
    public String remove(String functionalStageId) {
        return definitionIdByFunctionalStageId.remove(functionalStageId);
    }
}
