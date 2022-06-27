package com.maukaim.boule.flows.orchestrator.stage;

import com.maukaim.boule.flows.orchestrator.run.FlowRunLocked;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StageEventManager {

    private final Map<Class<? extends StageEvent>, StageEventProcessor<? extends StageEvent>> processorByEventType;

    public StageEventManager(Set<StageEventProcessor<? extends StageEvent>> stageEventProcessors) {
        Map<Class<? extends StageEvent>, StageEventProcessor<? extends StageEvent>> processorsByEventClass = new HashMap<>();

        for (StageEventProcessor<? extends StageEvent> stageEventProcessor : stageEventProcessors) {
            processorsByEventClass.compute(stageEventProcessor.getExpectedStageEventClass(), (key, processor) -> {
                if (processor != null) {
                    throw new IllegalArgumentException("Multiple processors proposed for StageEvent" + key.getSimpleName());
                }
                return stageEventProcessor;
            });
        }
        this.processorByEventType = Map.copyOf(processorsByEventClass);
    }

    public FlowRunLocked process(StageEvent event) {
        StageEventProcessor<? extends StageEvent> stageEventProcessor = this.processorByEventType.get(event.getClass());
        if (stageEventProcessor != null) {
            stageEventProcessor.castAndProcess(event);
        } else {
            throw new IllegalArgumentException("Subtype of StageEvent not supported: " + event.getClass().getSimpleName());
        }
        return null;
    }
}
