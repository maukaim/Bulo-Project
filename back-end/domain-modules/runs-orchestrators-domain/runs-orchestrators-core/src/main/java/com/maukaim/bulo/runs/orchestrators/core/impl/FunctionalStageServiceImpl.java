package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageService;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;

public class FunctionalStageServiceImpl implements FunctionalStageService {
    private final FunctionalStageStore functionalStageStore;

    public FunctionalStageServiceImpl(FunctionalStageStore functionalStageStore) {
        this.functionalStageStore = functionalStageStore;
    }

    @Override
    public String getDefinitionId(String id) {
        return this.functionalStageStore.getDefinitionId(id);
    }
}
