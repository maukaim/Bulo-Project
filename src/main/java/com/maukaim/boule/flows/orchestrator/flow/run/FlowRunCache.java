package com.maukaim.boule.flows.orchestrator.flow.run;

import com.maukaim.boule.flows.orchestrator.util.CloseableLock;

public interface FlowRunCache {
    FlowRun getRun(String flowRunId);

    FlowRun add(FlowRun flowRun);

    FlowRun update(FlowRun flowRun);

    CloseableLock<FlowRun> getAndLock(String runId);
}
