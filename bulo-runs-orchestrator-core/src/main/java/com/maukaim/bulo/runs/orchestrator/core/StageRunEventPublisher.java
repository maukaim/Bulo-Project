package com.maukaim.bulo.runs.orchestrator.core;

public interface StageRunEventPublisher {
    /**
     * Publish request to relevant microservice.
     * @param globalStageId concerned by the request
     * @param stageRunId assigned to the run.
     * @return did we publish request successfully?
     */
    boolean requestAsyncRunExecution(String globalStageId, String stageRunId);

    /**
     * Publish request to cancel a run to relevant microservice.
     * @param stageRunId concerned by the request.
     * @return was it canceled?
     */
    void requestAsyncRunCancellation(String stageRunId);

    /**
     * REST call to executor directly to request a cancel.
     * @param stageRunId concerned by the request.
     * @return was it canceled?
     */
    boolean requestSyncRunCancellation(String stageRunId, String executorId);
}
