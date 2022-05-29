package com.maukaim.boule.flows.orchestrator.cache;

import com.maukaim.boule.flows.orchestrator.Run;
import com.maukaim.boule.flows.orchestrator.RunStatus;

public interface FlowRunCache {
    Run getRun(String runId);
    void updateState(String runId, RunStatus status);
    void add(Run run);
    Run remove(String runId);
}
