package com.maukaim.bulo.executors.core.impl;

import com.maukaim.bulo.executors.data.runs.ExecutionCancelledException;
import com.maukaim.bulo.executors.core.RunOperator;
import com.maukaim.bulo.executors.core.StageRunConfig;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.StageRunner;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.data.result.StageRunStatus;

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
