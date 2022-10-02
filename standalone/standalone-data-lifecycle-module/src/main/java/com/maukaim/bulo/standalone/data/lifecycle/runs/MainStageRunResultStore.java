package com.maukaim.bulo.standalone.data.lifecycle.runs;

import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.standalone.data.lifecycle.StageRunResultListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.maukaim.bulo.standalone.data.lifecycle.runs.StandaloneUtils.EXECUTOR_ID;

public class MainStageRunResultStore implements StageRunResultStore {
    private final Map<String, StageRunResult> cache;
    private final List<StageRunResultListener> stageRunResultListeners;

    public MainStageRunResultStore(Map<String, StageRunResult> initialCache) {
        this.cache = new HashMap<>(initialCache);
        this.stageRunResultListeners = new ArrayList<>();
    }

    public void addListener(StageRunResultListener listener){
        this.stageRunResultListeners.add(listener);
    }

    @Override
    public StageRunResult put(StageRunResult result) {
        StageRunResult stageRunResult = this.cache.compute(result.getStageRunId(), (key, previous) -> {
            if (previous == null) {
                return result;
            }
            return previous.getStatus() != null && previous.getStatus().isTerminal() ?
                    previous : result;
        });
        notifyListener(stageRunResult);
        return stageRunResult;
    }

    private void notifyListener(StageRunResult result) {
        switch (result.getStatus()) {
            case ACKNOWLEDGED -> this.stageRunResultListeners.forEach((listener) -> listener.onAcknowledged(EXECUTOR_ID,result.getStageRunId(), Instant.now()));
            case RUNNING -> this.stageRunResultListeners.forEach((listener) -> listener.onStarted(result.getStageRunId(), Instant.now()));
            case CANCELLED -> this.stageRunResultListeners.forEach((listener) -> listener.onCancelled(result.getStageRunId(), Instant.now()));
            case FAILED -> this.stageRunResultListeners.forEach((listener) -> listener.onFailed(result.getStageRunId(), Instant.now()));
            case SUCCESS -> this.stageRunResultListeners.forEach((listener) -> listener.onSuccessful(result.getStageRunId(), Instant.now()));
        }
    }

    @Override
    public StageRunResult getById(String stageRunId) {
        return cache.get(stageRunId);
    }
}
