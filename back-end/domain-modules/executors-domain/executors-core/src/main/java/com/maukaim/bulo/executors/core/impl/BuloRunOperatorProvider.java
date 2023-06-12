package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.RunOperator;
import com.maukaim.bulo.executors.core.RunOperatorProvider;
import com.maukaim.bulo.executors.core.StageRunConfig;
import com.maukaim.bulo.executors.core.marshalling.MarshallerProvider;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.runners.api.RunnerMarshaller;
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
        if (runner == null
                || stageRunConfig == null
                || stageRunConfig.getInputsByName() == null
                || stageRunConfig.getParametersByName() == null){
            throw new IllegalArgumentException("Runner and/or stageRunConfig can't have null values.");
        }
        return new RunOperatorImpl(runner, stageRunConfig, stageRunResultStore, buildContext(stageRunConfig));
    }

    private StageRunnerContext buildContext(StageRunConfig config) {
        RunnerMarshaller runnerMarshaller = marshallerProvider.get();
        if(runnerMarshaller == null){
            throw new IllegalStateException("No marshaller found.");
        }
        return new StageRunnerContext(config.getInputsByName(),
                config.getParametersByName(),
                runnerMarshaller);
    }
}
