package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.data.models.StageRun;
import com.maukaim.bulo.runs.orchestrators.core.factories.StageRunFactory;

import java.time.Instant;
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

    @Override
    public Map<String, StageRun> startRuns(String flowRunId, Set<FlowStageId> stageIds) {
        Map<String, StageRun> result = new HashMap<>();
        for (FlowStageId stageId : stageIds) {
            StageRun newRunView = StageRunFactory.requested(flowRunId, stageId);
            boolean started = this.stageRunConnector.sendRun(stageId.getGlobalStageId(), newRunView.getStageRunId());
            this.stageRunStore.put(newRunView.getStageRunId(), newRunView.getFlowStageId(), newRunView.getFlowRunId());
            result.put(newRunView.getStageRunId(), started ? newRunView : StageRunFactory.failed(newRunView, Instant.now()));
        }
        return result;
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
