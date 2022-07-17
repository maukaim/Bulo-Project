package com.maukaim.bulo.runs.orchestrator.core.flowrun;

import com.maukaim.bulo.commons.core.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.model.FlowRun;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.model.StageRun;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public interface FlowRunService {
    FlowRun startRun(String flowId, Set<FlowStageId> rootStageIds);
    FlowRun getById(String flowRunId);
    FlowRun computeStageRunViewUnderLock(String flowRunId, Function<FlowRun, Map<String, StageRun>> flowRunUpdater);
}
