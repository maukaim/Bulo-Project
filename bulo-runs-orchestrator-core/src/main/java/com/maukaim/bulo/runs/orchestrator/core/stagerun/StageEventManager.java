package com.maukaim.bulo.runs.orchestrator.core.stagerun;

import com.maukaim.bulo.commons.io.IStageRunEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StageEventManager {

    private final Map<Class<? extends IStageRunEvent>, StageEventProcessor<? extends IStageRunEvent>> processorByEventType;
    private final StageRunCache stageRunCache;
    public StageEventManager(Set<StageEventProcessor<? extends IStageRunEvent>> stageEventProcessors,
                             StageRunCache stageRunCache) {
        this.stageRunCache = stageRunCache;

        Map<Class<? extends IStageRunEvent>, StageEventProcessor<? extends IStageRunEvent>> processorsByEventClass = new HashMap<>();
        for (StageEventProcessor<? extends IStageRunEvent> stageEventProcessor : stageEventProcessors) {
            processorsByEventClass.compute(stageEventProcessor.getExpectedStageEventClass(), (key, processor) -> {
                if (processor != null) {
                    throw new IllegalArgumentException("Multiple processors proposed for StageEvent" + key.getSimpleName());
                }
                return stageEventProcessor;
            });
        }
        this.processorByEventType = Map.copyOf(processorsByEventClass);
    }

    public void process(IStageRunEvent event) {
        StageEventProcessor<? extends IStageRunEvent> stageEventProcessor = this.processorByEventType.get(event.getClass());
        if (stageEventProcessor != null) {
            stageEventProcessor.castAndProcess(event, this.stageRunCache.getFlowRunId(event.getStageRunId())); //TODO Here don't manage yet if stageRunNotMapped to anyone;
        } else {
            throw new IllegalArgumentException("Subtype of StageEvent not supported: " + event.getClass().getSimpleName());
        }
    }
}
