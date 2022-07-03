package com.maukaim.bulo.runs.orchestrator.stage.run.impl;

import com.maukaim.bulo.flows.api.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.stage.run.StageRunCache;
import org.jgrapht.alg.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class StageRunCacheImpl implements StageRunCache {
    private Map<String, Pair<FlowStageId,String>> mappedStageRunId = new HashMap<>();

    @Override
    public void put(String stageRunId, FlowStageId stageId, String flowRunId) {
        this.mappedStageRunId.put(stageRunId, Pair.of(stageId, flowRunId));
    }

    @Override
    public String getFlowRunId(String stageRunId) {
        return this.mappedStageRunId.get(stageRunId).getSecond();
    }

    @Override
    public FlowStageId getStageId(String stageRunId) {
        return this.mappedStageRunId.get(stageRunId).getFirst();
    }
}
