package com.maukaim.bulo.standalone.app.connectivity;

import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.StageRunDependencyAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class StageRunConnectorImpl implements StageRunConnector {
    private final StageRunDependencyAdapter stageRunDependencyAdapter;
    private final StageRunEventProcessor stageRunEventProcessor;

    public StageRunConnectorImpl(StageRunDependencyAdapter stageRunDependencyAdapter, StageRunEventProcessor stageRunEventProcessor) {
        this.stageRunDependencyAdapter = stageRunDependencyAdapter;
        this.stageRunEventProcessor = stageRunEventProcessor;
    }

    @Override
    public boolean sendCancel(String stageRunId, String executorId) {
        this.stageRunEventProcessor.onCancelRequest(stageRunId);
        return true;
    }

    @Override
    public boolean sendRun(String stageId, String stageRunId, Set<StageRunDependency> stageRunDependencies) {
        this.stageRunEventProcessor.onRunRequest(stageId,
                stageRunId,
                resolve(stageRunDependencies));
        return true;
    }

    private Set<com.maukaim.bulo.executors.data.runs.StageRunDependency> resolve(Set<StageRunDependency> stageRunDependencies) {
        return stageRunDependencies == null ? Set.of() : stageRunDependencies.stream()
                .map(this.stageRunDependencyAdapter::adapte)
                .collect(Collectors.toSet());
    }
}
