package com.maukaim.bulo.runs.orchestrators.data;

public interface StageRunConnector {
    boolean sendCancel(String stageRunId, String executorId);
    boolean sendRun(String globalStageId, String stageRunId);
}
