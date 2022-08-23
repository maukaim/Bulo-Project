package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.lifecycle.resolver.StageRunEventResolver;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.executors.io.StageRunEventPublisher;
import com.maukaim.bulo.executors.io.out.StageRunEvent;

import java.util.HashMap;
import java.util.Map;

public class StageRunResultStoreImpl implements StageRunResultStore {
    private final Map<String, StageRunResult> cache;
    private final StageRunEventPublisher stageRunEventPublisher;
    private final StageRunEventResolver stageRunEventResolver;

    public StageRunResultStoreImpl(Map<String, StageRunResult> initialCache,
                                   StageRunEventPublisher stageRunEventPublisher,
                                   StageRunEventResolver stageRunEventResolver){
        this.cache = new HashMap<>(initialCache);
        this.stageRunEventPublisher = stageRunEventPublisher;
        this.stageRunEventResolver = stageRunEventResolver;
    }

    @Override
    public StageRunResult put(StageRunResult result) {
        this.cache.compute(result.getStageRunId(), (key,previous) -> {
            if(previous == null){
                return result;
            }
            return previous.getStatus() != null && previous.getStatus().isTerminal() ?
                    previous : result;
        });

        StageRunEvent event = stageRunEventResolver.resolve(result);
        if(event != null){
            this.stageRunEventPublisher.publish(event);
        }
        return result;
    }

    @Override
    public StageRunResult getById(String stageRunId) {
        return cache.get(stageRunId);
    }
}
