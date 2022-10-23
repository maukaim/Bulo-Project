package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.*;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class StageRunServiceImpl implements StageRunService {
    private final StageRunConnector stageRunConnector;
    private final StageRunStore stageRunStore;

    public StageRunServiceImpl(StageRunConnector stageRunConnector,
                               StageRunStore stageRunStore) {
        this.stageRunConnector = stageRunConnector;
        this.stageRunStore = stageRunStore;
    }

    public Map<String, StageRun> getNextStageRuns(Context<?> context, Map<ContextualizedStageId, Set<StageRunDependency>> flowStageToRunByDependencies) {
        Map<String, StageRun> result = new HashMap<>();
        for (ContextualizedStageId contextualizedStageId : flowStageToRunByDependencies.keySet()) {
            Set<StageRunDependency> stageRunDependencies = flowStageToRunByDependencies.get(contextualizedStageId);
            TechnicalStageRun newRunView = TechnicalStageRunFactory.toBeRequested(context, contextualizedStageId, stageRunDependencies);
            this.stageRunStore.put(newRunView.getStageRunId(), newRunView);
            result.put(newRunView.getStageRunId(), newRunView);
        }
        return result;
    }

    @Override
    public Map<String, StageRun> startRuns(Collection<TechnicalStageRun> toBeRequestedTechnicalStageRuns) {
        Map<String, StageRun> result = new HashMap<>();
        for (TechnicalStageRun technicalStageRunToBeRequested : toBeRequestedTechnicalStageRuns) {
            boolean started = this.stageRunConnector.sendRun(technicalStageRunToBeRequested.getContextualizedStageId().getStageId(), technicalStageRunToBeRequested.getStageRunId(), technicalStageRunToBeRequested.getStageRunDependencies());
            result.put(technicalStageRunToBeRequested.getStageRunId(), started ?
                    TechnicalStageRunFactory.requested(technicalStageRunToBeRequested) :
                    TechnicalStageRunFactory.failed(technicalStageRunToBeRequested, Instant.now()));
        }
        return result;
    }

    @Override
    public StageRun getById(String stageRunId) {
        return this.stageRunStore.getById(stageRunId);
    }

    @Override
    public void requestCancel(String stageRunId) {
        this.stageRunStore.getById(stageRunId);
        this.stageRunConnector.sendCancel(stageRunId, null);
    }

    @Override
    public void requestCancel(String stageRunId, String executorId) {
        boolean requested = this.stageRunConnector.sendCancel(stageRunId, executorId);
        if (!requested) {
            System.out.println(String.format("LogTemp:::WARN Cancel request to executor %s did not succeed", executorId));
        }
    }

    @Override
    public FunctionalStageRun computeStageRunUpdateUnderLock(String contextId, Function<FunctionalStageRun, Map<String, StageRun>> contextUpdator) {

    }
}
