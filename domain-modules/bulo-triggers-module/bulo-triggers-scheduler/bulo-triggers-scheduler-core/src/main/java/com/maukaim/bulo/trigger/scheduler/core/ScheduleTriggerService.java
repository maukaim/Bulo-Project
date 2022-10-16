package com.maukaim.bulo.trigger.scheduler.core;


import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

public class ScheduleTriggerService {
    private final CronExecutorService executorService;
    private final TriggerConnector triggerConnector;
    private final Map<TriggerId, ScheduledFuture<?>> scheduledFutureCache = new ConcurrentHashMap<>();

    public ScheduleTriggerService(CronExecutorService executorService,
                                  TriggerConnector triggerConnector) {
        this.executorService = executorService;
        this.triggerConnector = triggerConnector;
    }

    public void setTrigger(TriggerId triggerId, String cronExpression) {
        Runnable task = () -> this.triggerConnector.requestTrigger(
                triggerId.getFlowId(),
                triggerId.getFlowStageIds()
        );

        ScheduledFuture<?> scheduledFuture = this.executorService.schedule(task, cronExpression);
        this.scheduledFutureCache.compute(triggerId, (k, v) -> {
            if (v != null) {
                v.cancel(true);
            }
            return scheduledFuture;
        });
    }

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
