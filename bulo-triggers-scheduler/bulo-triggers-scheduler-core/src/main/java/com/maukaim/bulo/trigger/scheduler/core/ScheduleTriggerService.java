package com.maukaim.bulo.trigger.scheduler.core;


import com.maukaim.bulo.commons.models.FlowStageId;
import com.maukaim.bulo.commons.models.TriggerId;
import com.maukaim.bulo.triggers.scheduler.data.ScheduleTriggerConfig;
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

    public ScheduleTriggerConfig setTrigger(ScheduleTriggerConfig triggerConfig) {
        Runnable task = () -> this.triggerConnector.requestTrigger(
                triggerConfig.getTriggerId().getFlowId(),
                triggerConfig.getTriggerId().getFlowStageIds()
        );

        ScheduledFuture<?> scheduledFuture = this.executorService.schedule(task, triggerConfig.getCronExpression());
        this.scheduledFutureCache.compute(triggerConfig.getTriggerId(), (k, v) -> {
            if (v != null) {
                v.cancel(true);
            }
            return scheduledFuture;
        });
        return triggerConfig;
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
