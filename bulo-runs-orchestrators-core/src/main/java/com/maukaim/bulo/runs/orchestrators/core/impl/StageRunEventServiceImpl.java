package com.maukaim.bulo.runs.orchestrators.core.impl;

import com.maukaim.bulo.commons.io.IStageRunEvent;
import com.maukaim.bulo.runs.orchestrators.core.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.StageRunEventService;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StageRunEventServiceImpl implements StageRunEventService {

    private final Map<Class<? extends IStageRunEvent>, StageEventProcessor<? extends IStageRunEvent>> processorByEventType;
    private final StageRunStore stageRunStore;
    public StageRunEventServiceImpl(Set<StageEventProcessor<? extends IStageRunEvent>> stageEventProcessors,
                                    StageRunStore stageRunStore) {
        this.stageRunStore = stageRunStore;

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

    @Override
    public void process(IStageRunEvent event) {
        StageEventProcessor<? extends IStageRunEvent> stageEventProcessor = this.processorByEventType.get(event.getClass());
        if (stageEventProcessor != null) {
            stageEventProcessor.castAndProcess(event, this.stageRunStore.getFlowRunId(event.getStageRunId()));
        } else {
            throw new IllegalArgumentException("Subtype of StageEvent not supported: " + event.getClass().getSimpleName());
        }
    }
}
