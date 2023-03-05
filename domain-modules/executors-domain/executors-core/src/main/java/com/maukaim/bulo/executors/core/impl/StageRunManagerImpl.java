package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.*;
import com.maukaim.bulo.runners.api.StageRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

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

    @Override
    public boolean cancel(String stageRunId) {
        if(this.scheduledStageRun.containsKey(stageRunId)){
            Future<?> future = this.scheduledStageRun.get(stageRunId);
            return future.cancel(true);
        }else{
            return false;
        }
    }
}
