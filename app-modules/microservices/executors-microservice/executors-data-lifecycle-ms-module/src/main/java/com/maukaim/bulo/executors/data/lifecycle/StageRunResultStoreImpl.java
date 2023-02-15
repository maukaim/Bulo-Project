package com.maukaim.bulo.executors.data.lifecycle;

import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunResultDtoAdapter;
import com.maukaim.bulo.executors.data.lifecycle.resolver.StageRunEventResolver;
import com.maukaim.bulo.executors.data.result.StageRunResult;
import com.maukaim.bulo.io.executors.StageRunEventPublisher;
import com.maukaim.bulo.io.executors.StageRunResultEventPublisher;
import com.maukaim.bulo.io.executors.out.StageRunEvent;
import com.maukaim.bulo.io.executors.out.StageRunResultEvent;
import com.maukaim.bulo.io.executors.out.model.StageRunResultDto;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class StageRunResultStoreImpl implements StageRunResultStore {
    private final Map<String, StageRunResult> cache;
    private final StageRunEventPublisher stageRunEventPublisher;
    private final StageRunEventResolver stageRunEventResolver;
    private final StageRunResultEventPublisher stageRunResultEventPublisher;
    private final StageRunResultDtoAdapter stageRunResultDtoAdapter;

    public StageRunResultStoreImpl(Map<String, StageRunResult> initialCache,
                                   StageRunEventPublisher stageRunEventPublisher,
                                   StageRunEventResolver stageRunEventResolver,
                                   StageRunResultEventPublisher stageRunResultEventPublisher,
                                   StageRunResultDtoAdapter stageRunResultDtoAdapter) {
        this.cache = new HashMap<>(initialCache);
        this.stageRunEventPublisher = stageRunEventPublisher;
        this.stageRunEventResolver = stageRunEventResolver;
        this.stageRunResultEventPublisher = stageRunResultEventPublisher;
        this.stageRunResultDtoAdapter = stageRunResultDtoAdapter;
    }

    @Override
    public StageRunResult put(StageRunResult result) {
        StageRunResult savedRecord;
        this.save(result);
        boolean published = publishStageRunResultEvent(result);
        savedRecord = published ? result : save(result);
        publishStageRunEvent(result);
        return savedRecord;
    }

    @Override
    public StageRunResult getById(String stageRunId) {
        return cache.get(stageRunId);
    }

    public StageRunResult save(StageRunResult result) {
        return this.cache.compute(result.getStageRunId(), (key, previous) -> {
            if (previous == null) {
                return result;
            }
            return previous.getStatus() != null && previous.getStatus().isTerminal() ?
                    previous : result;
        });
    }

    private boolean publishStageRunResultEvent(StageRunResult result) {
        StageRunResultDto dto = this.stageRunResultDtoAdapter.adapte(result);
        return this.stageRunResultEventPublisher.publish(new StageRunResultEvent(dto, Instant.now()));
    }

    private void publishStageRunEvent(StageRunResult result) {
        StageRunEvent event = stageRunEventResolver.resolve(result);
        if (event != null) {
            this.stageRunEventPublisher.publish(event);
        }
    }
}
