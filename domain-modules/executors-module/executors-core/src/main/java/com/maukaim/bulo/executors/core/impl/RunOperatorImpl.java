package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.core.RunOperator;
import com.maukaim.bulo.executors.core.StageRunConfig;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.data.result.StageRunStatus;
import com.maukaim.bulo.runners.api.ExecutionCancelledException;
import com.maukaim.bulo.runners.api.StageRunner;
import com.maukaim.bulo.runners.api.StageRunnerContext;

import java.util.Map;

public class RunOperatorImpl implements RunOperator {
    private final StageRunner runner;
    private final StageRunConfig config;
    private final StageRunResultStore stageRunResultStore;
    private final StageRunnerContext ctx;

    public RunOperatorImpl(StageRunner runner, StageRunConfig config, StageRunResultStore stageRunResultStore, StageRunnerContext ctx) {
        this.runner = runner;
        this.config = config;
        this.stageRunResultStore = stageRunResultStore;
        this.ctx = ctx;
    }

    @Override
    public void run() {
        StageRunResult runResult = null;
        try {
            this.stageRunResultStore.put(StageRunResult.of(config.getStageRunId(), StageRunStatus.RUNNING));
            Map<String, String> runOutput = this.runner.run(ctx);
            runResult = new StageRunResult(config.getStageRunId(), StageRunStatus.SUCCESS, runOutput);
        } catch (ExecutionCancelledException cancellationThrowable) {
            //TODO: duplicate of Cancelllation info. Better handle this.
            // What about the CancellableThread idea?
            runResult = StageRunResult.of(config.getStageRunId(), StageRunStatus.CANCELLED);
        } catch (Throwable t) {
            runResult = StageRunResult.of(config.getStageRunId(), StageRunStatus.FAILED);
        } finally {
            if (runResult != null) {
                this.stageRunResultStore.put(runResult);
            } else {
                System.out.println("Hmm.... no runResult, bizarre cette histoire");
                //TODO: log problem.
            }
        }
    }
}
