package com.maukaim.bulo.standalone.data.lifecycle;

import java.time.Instant;

public interface StageRunResultListener {
    void onAcknowledged(String executorId, String stageRunId, Instant now);
    void onStarted(String stageRunId, Instant now);
    void onCancelled(String stageRunId, Instant now);
    void onFailed(String stageRunId, Instant now);
    void onSuccessful(String stageRunId, Instant now);
}
