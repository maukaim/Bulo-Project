package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.RunOperator;
import com.maukaim.bulo.executors.core.RunOperatorProvider;
import com.maukaim.bulo.executors.core.StageRunConfig;
import com.maukaim.bulo.executors.core.marshalling.MarshallerProvider;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.runners.api.StageRunner;
import com.maukaim.bulo.runners.api.StageRunnerContext;

public class BuloRunOperatorProvider implements RunOperatorProvider {
    private final StageRunResultStore stageRunResultStore;
    private final MarshallerProvider marshallerProvider;

    public BuloRunOperatorProvider(StageRunResultStore stageRunResultStore,
                                   MarshallerProvider marshallerProvider) {
        this.stageRunResultStore = stageRunResultStore;
        this.marshallerProvider = marshallerProvider;
    }

    @Override
    public RunOperator get(StageRunner runner, StageRunConfig stageRunConfig) {
        return new RunOperatorImpl(runner, stageRunConfig, stageRunResultStore, buildContext(stageRunConfig));
    }

    private StageRunnerContext buildContext(StageRunConfig config) {
        return new StageRunnerContext(config.getInputsByName(),
                config.getParametersByName(),
                marshallerProvider.get());
    }
}
