package com.maukaim.bulo.runs.orchestrator.flow.run;

import com.maukaim.bulo.runs.orchestrator.util.CloseableEntityLock;

public interface FlowRunCache {
    FlowRun getRun(String flowRunId);

    FlowRun add(FlowRun flowRun);

    FlowRun update(FlowRun flowRun);

    CloseableEntityLock<FlowRun> getAndLock(String runId);
}
