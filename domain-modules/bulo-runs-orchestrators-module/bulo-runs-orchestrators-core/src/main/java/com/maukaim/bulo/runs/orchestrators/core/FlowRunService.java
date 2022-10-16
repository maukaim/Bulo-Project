package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;
import com.maukaim.bulo.runs.orchestrators.data.runs.stage.StageRun;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public interface FlowRunService {
    FlowRun startRun(String flowId, Set<FlowStageId> rootStageIds);
    FlowRun getById(String flowRunId);
    FlowRun computeStageRunViewUnderLock(String flowRunId, Function<FlowRun, Map<String, StageRun>> flowRunUpdater);
}
