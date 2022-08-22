package com.maukaim.bulo.executor.core.impl;

import com.maukaim.bulo.executor.core.RunOperator;
import com.maukaim.bulo.executor.core.RunOperatorProvider;
import com.maukaim.bulo.executor.core.StageRunConfig;
import com.maukaim.bulo.executor.core.api.StageRunResultStore;
import com.maukaim.bulo.executor.core.api.StageRunner;
import com.maukaim.bulo.executor.core.impl.RunOperatorImpl;

public class RunOperatorProviderImpl implements RunOperatorProvider {
    private final StageRunResultStore stageRunResultStore;

    public RunOperatorProviderImpl(StageRunResultStore stageRunResultStore) {
        this.stageRunResultStore = stageRunResultStore;
    }

    @Override
    public RunOperator get(StageRunner runner, StageRunConfig stageRunConfig) {
        return new RunOperatorImpl(runner, stageRunConfig, stageRunResultStore);
    }
}
