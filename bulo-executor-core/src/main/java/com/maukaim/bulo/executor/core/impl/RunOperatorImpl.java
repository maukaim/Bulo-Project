package com.maukaim.bulo.executor.core.impl;

import com.maukaim.bulo.executor.core.RunOperator;
import com.maukaim.bulo.executor.core.StageRunConfig;
import com.maukaim.bulo.executor.core.api.StageRunResultStore;
import com.maukaim.bulo.executor.core.api.StageRunner;
import com.maukaim.bulo.executor.core.api.result.StageRunResult;
import com.maukaim.bulo.executor.core.api.result.StageRunStatus;

import java.util.Map;

public class RunOperatorImpl implements RunOperator {
    private StageRunner runner;
    private StageRunConfig config;
    private StageRunResultStore stageRunResultStore;

    public RunOperatorImpl(StageRunner runner, StageRunConfig config, StageRunResultStore stageRunResultStore) {
        this.runner = runner;
        this.config = config;
        this.stageRunResultStore = stageRunResultStore;
    }

    @Override
    public void run() {
        StageRunResult runResult = null;
        try {
            this.stageRunResultStore.put(StageRunResult.of(config.getStageRunId(), StageRunStatus.RUNNING));
            Map<String, String> runOutput = this.runner.run(config.getInputsByName(), config.getParametersByName());
            runResult = new StageRunResult(config.getStageRunId(), StageRunStatus.SUCCESS, runOutput);
        } catch (Throwable t) {
            runResult = StageRunResult.of(config.getStageRunId(), StageRunStatus.FAILED);
        } finally {
            if (runResult != null) {
                this.stageRunResultStore.put(runResult);
            } else {
                //TODO: log problem.
            }
        }
    }
}
