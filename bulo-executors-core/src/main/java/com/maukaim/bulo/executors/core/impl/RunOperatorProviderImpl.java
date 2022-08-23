package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.RunOperator;
import com.maukaim.bulo.executors.core.RunOperatorProvider;
import com.maukaim.bulo.executors.core.StageRunConfig;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.StageRunner;

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
