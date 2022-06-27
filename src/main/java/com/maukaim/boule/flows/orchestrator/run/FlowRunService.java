package com.maukaim.boule.flows.orchestrator.run;

import com.maukaim.boule.flows.orchestrator.stage.run.StageRunView;

import java.util.Map;
import java.util.function.Function;

public interface FlowRunService {
    FlowRun startRun(String flowId, String rootStageId);
    FlowRun getById(String flowRunId);
    FlowRun computeStageRunViewUnderLock(String flowRunId, Function<FlowRun, Map<String, StageRunView>> flowRunUpdater);
}
