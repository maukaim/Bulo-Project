package com.maukaim.bulo.runs.orchestrators.data.lifecycle;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.models.Pair;

import java.util.HashMap;
import java.util.Map;

public class StageRunStoreImpl implements StageRunStore {
    private Map<String, Pair<FlowStageId, String>> mappedStageRunId = new HashMap<>();

    @Override
    public void put(String stageRunId, FlowStageId stageId, String flowRunId) {
        this.mappedStageRunId.put(stageRunId, Pair.of(stageId, flowRunId));
    }

    @Override
    public String getFlowRunId(String stageRunId) {
        return this.mappedStageRunId.get(stageRunId).getSecond();
    }
}
