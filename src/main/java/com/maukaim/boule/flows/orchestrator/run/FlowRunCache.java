package com.maukaim.boule.flows.orchestrator.run;

import com.maukaim.boule.flows.orchestrator.util.CloseableLock;

/**
 * IDEA: Faire un EntityCache<KEY,ENTITY> abstract. Il implement une maniere lock d'acceder aux ressources, avec des ReentrantLock sur chaque entity.
 */
public interface FlowRunCache {
    FlowRun getRun(String flowRunId);

    FlowRun add(FlowRun flowRun);

    FlowRun remove(String flowRunId);

    FlowRun update(FlowRun flowRun);

    CloseableLock<FlowRun> getAndLock(String runId);
}
