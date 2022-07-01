package com.maukaim.boule.flows.orchestrator.flow.run;

import com.maukaim.boule.flows.orchestrator.util.CloseableEntityLock;

public interface FlowRunCache {
    FlowRun getRun(String flowRunId);

    FlowRun add(FlowRun flowRun);

    FlowRun update(FlowRun flowRun);

    CloseableEntityLock<FlowRun> getAndLock(String runId);
}
