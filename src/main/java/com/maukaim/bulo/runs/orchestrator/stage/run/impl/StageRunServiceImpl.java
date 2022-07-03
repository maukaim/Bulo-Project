package com.maukaim.bulo.runs.orchestrator.stage.run.impl;

import com.maukaim.bulo.flows.api.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.publisher.StageRunEventPublisher;
import com.maukaim.bulo.runs.orchestrator.stage.run.StageRunCache;
import com.maukaim.bulo.runs.orchestrator.stage.run.StageRunService;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunView;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunViewFactory;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StageRunServiceImpl implements StageRunService {
    private final StageRunEventPublisher stageRunEventPublisher;
    private final StageRunCache stageRunCache;

    public StageRunServiceImpl(StageRunEventPublisher stageRunEventPublisher, StageRunCache stageRunCache) {
        this.stageRunEventPublisher = stageRunEventPublisher;
        this.stageRunCache = stageRunCache;
    }

    @Override
    public Map<String, StageRunView> startRuns(String flowRunId, Set<FlowStageId> stageIds) {
        Map<String, StageRunView> result = new HashMap<>();
        for (FlowStageId stageId : stageIds) {
            StageRunView newRunView = StageRunViewFactory.requested(flowRunId, stageId);
            boolean started = this.stageRunEventPublisher.requestAsyncRunExecution(stageId.getGlobalStageId(), newRunView.getStageRunId());
            this.stageRunCache.put(newRunView.getStageRunId(), newRunView.getFlowStageId(), newRunView.getFlowRunId());
            result.put(newRunView.getStageRunId(), started ? newRunView : StageRunViewFactory.failed(newRunView, Instant.now()));
        }
        return result;
    }

    @Override
    public void requestCancel(String stageRunId) {
        this.stageRunEventPublisher.requestAsyncRunCancellation(stageRunId);
    }

    @Override
    public void requestCancel(String stageRunId, String executorId) {
        boolean requested = this.stageRunEventPublisher.requestSyncRunCancellation(stageRunId, executorId);
        if(!requested){
            System.out.println(String.format("LogTemp:::WARN Cancel request to executor %s did not succeed", executorId));
        }
    }
}
