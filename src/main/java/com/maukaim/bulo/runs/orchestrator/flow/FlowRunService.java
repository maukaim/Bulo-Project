package com.maukaim.bulo.runs.orchestrator.flow;

import com.maukaim.bulo.runs.orchestrator.flow.run.FlowRun;
import com.maukaim.bulo.runs.orchestrator.flow.view.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.stage.run.model.StageRunView;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public interface FlowRunService {
    FlowRun startRun(String flowId, Set<FlowStageId> rootStageIds);
    FlowRun getById(String flowRunId);
    FlowRun computeStageRunViewUnderLock(String flowRunId, Function<FlowRun, Map<String, StageRunView>> flowRunUpdater);
}
