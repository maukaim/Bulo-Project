package com.maukaim.bulo.executor.core.impl;

import com.maukaim.bulo.executor.core.*;
import com.maukaim.bulo.executor.core.api.StageRunResultStore;
import com.maukaim.bulo.executor.core.api.StageRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;


/**
 * Initialized with Embedded Runners.
 */
public class StageRunManagerImpl implements StageRunManager {
    private final StageRunnerRegistry runnerRegistry;
    private final RunExecutor runExecutor;
    private final RunOperatorProvider runOperatorProvider;
    private final Map<String, Future<?>> scheduledStageRun = new HashMap<>();

    public StageRunManagerImpl(StageRunnerRegistry runnerRegistry,
                               RunExecutor runExecutor,
                               RunOperatorProvider runOperatorProvider) {
        this.runOperatorProvider = runOperatorProvider;
        this.runnerRegistry=runnerRegistry;
        this.runExecutor = runExecutor;
    }

    @Override
    public boolean submit(StageRunConfig config) {
        try{
            StageRunner stageRunner = this.runnerRegistry.getByDefinitionId(config.getDefinitionId());
            if(stageRunner == null){
                return false;
            }
            RunOperator runOperator = runOperatorProvider.get(stageRunner, config);
            Future<?> scheduledRun = this.runExecutor.async(runOperator);
            this.scheduledStageRun.put(config.getStageRunId(), scheduledRun);
            return true;
        }catch (Throwable ignored){
        }
        return false;
    }
}
