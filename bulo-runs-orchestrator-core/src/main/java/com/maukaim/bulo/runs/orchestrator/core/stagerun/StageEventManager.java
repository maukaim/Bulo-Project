package com.maukaim.bulo.runs.orchestrator.core.stagerun;

import com.maukaim.bulo.runs.orchestrator.io.in.StageRunEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StageEventManager {

    private final Map<Class<? extends StageRunEvent>, StageEventProcessor<? extends StageRunEvent>> processorByEventType;
    private final StageRunCache stageRunCache;
    public StageEventManager(Set<StageEventProcessor<? extends StageRunEvent>> stageEventProcessors,
                             StageRunCache stageRunCache) {
        this.stageRunCache = stageRunCache;

        Map<Class<? extends StageRunEvent>, StageEventProcessor<? extends StageRunEvent>> processorsByEventClass = new HashMap<>();
        for (StageEventProcessor<? extends StageRunEvent> stageEventProcessor : stageEventProcessors) {
            processorsByEventClass.compute(stageEventProcessor.getExpectedStageEventClass(), (key, processor) -> {
                if (processor != null) {
                    throw new IllegalArgumentException("Multiple processors proposed for StageEvent" + key.getSimpleName());
                }
                return stageEventProcessor;
            });
        }
        this.processorByEventType = Map.copyOf(processorsByEventClass);
    }

    public void process(StageRunEvent event) {
        StageEventProcessor<? extends StageRunEvent> stageEventProcessor = this.processorByEventType.get(event.getClass());
        if (stageEventProcessor != null) {
            stageEventProcessor.castAndProcess(event, this.stageRunCache.getFlowRunId(event.getStageRunId())); //TODO Here don't manage yet if stageRunNotMapped to anyone;
        } else {
            throw new IllegalArgumentException("Subtype of StageEvent not supported: " + event.getClass().getSimpleName());
        }
    }
}
