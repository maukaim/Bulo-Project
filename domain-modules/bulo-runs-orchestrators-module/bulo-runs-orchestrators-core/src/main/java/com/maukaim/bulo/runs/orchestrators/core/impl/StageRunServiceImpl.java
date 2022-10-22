package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;
import com.maukaim.bulo.runs.orchestrators.core.factories.StageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRunDependency;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StageRunServiceImpl implements StageRunService {
    private final StageRunConnector stageRunConnector;
    private final StageRunStore stageRunStore;

    public StageRunServiceImpl(StageRunConnector stageRunConnector,
                               StageRunStore stageRunStore) {
        this.stageRunConnector = stageRunConnector;
        this.stageRunStore = stageRunStore;
    }

    public Map<String,StageRun> getNextStageRun(String flowRunId, Map<ContextualizedStageId, Set<StageRunDependency>> flowStageToRunByDependencies){
        Map<String, StageRun> result = new HashMap<>();
        for (ContextualizedStageId contextualizedStageId : flowStageToRunByDependencies.keySet()) {
            Set<StageRunDependency> stageRunDependencies = flowStageToRunByDependencies.get(contextualizedStageId);
            StageRun newRunView = StageRunFactory.toBeRequested(flowRunId, contextualizedStageId, stageRunDependencies);
            this.stageRunStore.put(newRunView.getStageRunId(), newRunView);
            result.put(newRunView.getStageRunId(), newRunView);
        }
        return result;
    }

    @Override
    public Map<String, StageRun> startRuns(Collection<StageRun> toBeRequestedStageRuns) {
        Map<String, StageRun> result = new HashMap<>();
        for (StageRun stageRunToBeRequested : toBeRequestedStageRuns) {
            boolean started = this.stageRunConnector.sendRun(stageRunToBeRequested.getFlowStageId().getStageId(), stageRunToBeRequested.getStageRunId(), stageRunToBeRequested.getStageRunDependencies());
            result.put(stageRunToBeRequested.getStageRunId(), started ? StageRunFactory.requested(stageRunToBeRequested) : StageRunFactory.failed(stageRunToBeRequested, Instant.now()));
        }
        return result;
    }

    @Override
    public StageRun getById(String stageRunId) {
        return this.stageRunStore.getById(stageRunId);
    }

    @Override
    public void requestCancel(String stageRunId) {
        this.stageRunConnector.sendCancel(stageRunId, null);
    }

    @Override
    public void requestCancel(String stageRunId, String executorId) {
        boolean requested = this.stageRunConnector.sendCancel(stageRunId, executorId);
        if(!requested){
            System.out.println(String.format("LogTemp:::WARN Cancel request to executor %s did not succeed", executorId));
        }
    }
}
