package com.maukaim.bulo.standalone.app.connectivity;

import com.maukaim.bulo.executors.core.StageRunProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.RunDependency;
import com.maukaim.bulo.standalone.data.lifecycle.StageRunResultListener;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.StageRunDependencyAdapter;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class StageRunConnectorImpl implements StageRunConnector {
    private final StageRunDependencyAdapter stageRunDependencyAdapter;
    private final StageRunProcessor stageRunProcessor;
    private StageRunResultListener stageRunResultListener;

    public StageRunConnectorImpl(StageRunDependencyAdapter stageRunDependencyAdapter,
                                 StageRunProcessor stageRunProcessor) {
        this.stageRunDependencyAdapter = stageRunDependencyAdapter;
        this.stageRunProcessor = stageRunProcessor;
    }

    public void setStageRunResultListener(StageRunResultListener stageRunResultListener) {
        this.stageRunResultListener = stageRunResultListener;
    }

    @Override
    public boolean requestCancel(String stageRunId, String executorId) {
        this.stageRunProcessor.onCancelRequest(stageRunId);
        return true;
    }

    @Override
    public boolean requestRun(String stageId, String stageRunId, Set<RunDependency> stageRunDependencies) {
        this.stageRunProcessor.onRunRequest(stageId,
                stageRunId,
                resolve(stageRunDependencies));
        return true;
    }

    @Override
    public void propagateFunctionalStageRunAcknowleged(String stageRunId) {
        this.stageRunResultListener.onAcknowledged("STANDALONE_APP", stageRunId, Instant.now());
    }

    @Override
    public void propagateFunctionalStageRunStarted(String stageRunId) {
        this.stageRunResultListener.onStarted(stageRunId, Instant.now());
    }

    @Override
    public void propagateFunctionalStageRunCancelled(String stageRunId) {
        this.stageRunResultListener.onCancelled(stageRunId, Instant.now());
    }

    @Override
    public void propagateFunctionalStageRunFailed(String stageRunId) {
        this.stageRunResultListener.onFailed(stageRunId, Instant.now());
    }

    @Override
    public void propagateFunctionalStageRunSuccessful(String stageRunId) {
        this.stageRunResultListener.onSuccessful(stageRunId, Instant.now());
    }

    private Set<com.maukaim.bulo.executors.data.runs.StageRunDependency> resolve(Set<RunDependency> stageRunDependencies) {
        return stageRunDependencies == null ? Set.of() : stageRunDependencies.stream()
                .map(this.stageRunDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
