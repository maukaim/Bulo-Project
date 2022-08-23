package com.maukaim.bulo.trigger.scheduler.core;

import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConfig;
import com.maukaim.bulo.triggers.core.TriggerEventPublisher;
import com.maukaim.bulo.triggers.core.TriggerService;
import com.maukaim.bulo.triggers.io.TriggerId;
import com.maukaim.bulo.triggers.io.out.BasicTriggerEvent;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

public class ScheduleTriggerService implements TriggerService<ScheduleTriggerConfig> {
    private final CronExecutorService executorService;
    private final TriggerEventPublisher eventPublisher;
    private final Map<TriggerId, ScheduledFuture<?>> scheduledFutureCache = new ConcurrentHashMap<>();

    public ScheduleTriggerService(CronExecutorService executorService,
                                  TriggerEventPublisher eventPublisher) {
        this.executorService = executorService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public ScheduleTriggerConfig setTrigger(ScheduleTriggerConfig triggerConfig) {
        Runnable task = () -> this.eventPublisher.publish(new BasicTriggerEvent(
                triggerConfig.getTriggerId().getFlowId(),
                triggerConfig.getTriggerId().getFlowStageIds(),
                Instant.now()));

        ScheduledFuture<?> scheduledFuture = this.executorService.schedule(task, triggerConfig.getCronExpression());
        this.scheduledFutureCache.compute(triggerConfig.getTriggerId(), (k, v) -> {
            if (v != null) {
                v.cancel(true);
            }
            return scheduledFuture;
        });
        return triggerConfig;
    }

    @Override
    public boolean removeTrigger(String flowId, Set<FlowStageId> stageIds) {
        TriggerId key = TriggerId.of(flowId, stageIds);
        if (this.scheduledFutureCache.containsKey(key)) {
            ScheduledFuture<?> removedItem = this.scheduledFutureCache.remove(key);
            removedItem.cancel(true);
            return removedItem != null;
        }
        return false;
    }
}
