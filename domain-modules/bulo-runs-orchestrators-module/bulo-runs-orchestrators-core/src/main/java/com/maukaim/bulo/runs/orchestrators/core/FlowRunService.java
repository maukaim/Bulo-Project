package com.maukaim.bulo.runs.orchestrators.core;

import com.maukaim.bulo.commons.models.ContextualizedStageId;
import com.maukaim.bulo.runs.orchestrators.data.runs.flow.FlowRun;

import java.util.Set;

public interface FlowRunService extends OrchestrableContextService<FlowRun, String> {
    FlowRun startRun(String flowId, Set<ContextualizedStageId> rootStageIds);

    FlowRun getById(String flowRunId);
}
