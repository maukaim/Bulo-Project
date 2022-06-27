package com.maukaim.boule.flows.orchestrator.publisher;

import java.util.Set;

public interface StageRunEventPublisher {
    /**
     * Publish request to relevant micro-service.
     * @param flowRunId concerned by the request.
     * @param stageIds concerned by the request.
     * @return did we publish request successfully?
     */
    boolean requestAsyncRun(String flowRunId, Set<String> stageIds);

    /**
     * Publish request to cancel a run to relevant micro-service.
     * @param flowRunId concerned by the request
     * @param stageId concerned by the request.
     * @return was it canceled?
     */
    boolean requestSyncCancel(String flowRunId, String stageId);
}
