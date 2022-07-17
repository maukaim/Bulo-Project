package com.maukaim.bulo.runs.orchestrator.core.flowrun;

import com.maukaim.bulo.runs.orchestrator.core.flowrun.model.FlowRun;
import com.maukaim.bulo.runs.orchestrator.core.util.CloseableEntityLock;

public interface FlowRunCache {
    FlowRun getRun(String flowRunId);

    FlowRun add(FlowRun flowRun);

    FlowRun update(FlowRun flowRun);

    CloseableEntityLock<FlowRun> getAndLock(String runId);
}
