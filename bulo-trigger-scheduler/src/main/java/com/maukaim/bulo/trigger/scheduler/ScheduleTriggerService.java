package com.maukaim.bulo.trigger.scheduler;

import com.google.common.collect.Maps;
import com.maukaim.bulo.trigger.core.TriggerEventPublisher;
import com.maukaim.bulo.trigger.core.TriggerService;
import com.maukaim.bulo.triggers.api.TriggerEvent;
import com.maukaim.bulo.triggers.api.TriggerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleTriggerService implements TriggerService<ScheduleTriggerConfig> {
    private ThreadPoolTaskScheduler executors = new ThreadPoolTaskScheduler();
    private Map<TriggerId, ScheduledFuture<?>> scheduledFutureCache = Maps.newConcurrentMap();
    private TriggerEventPublisher eventPublisher;

    public ScheduleTriggerService( @Autowired TriggerEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        this.init();
    }

    private void init() {
        System.out.println("Initializing ScheduleTriggerService...");
        try {
            this.executors.initialize();
            this.executors.setPoolSize(4);
            this.executors.setThreadNamePrefix("FlowExecutor-d%");
            this.executors.setRemoveOnCancelPolicy(true);
            this.executors.setErrorHandler(throwable -> {
                System.out.println("While executing task -> " + throwable.getMessage());
            });
        } catch (Throwable e) {
            System.out.println("While initializing ScheduleTriggerService -> " + e.getMessage());
        }
    }


    @Override
    public ScheduleTriggerConfig setTrigger(ScheduleTriggerConfig triggerConfig) {
        Runnable task = () -> this.eventPublisher.publish(new TriggerEvent(triggerConfig.getTriggerId(), Instant.now()));
        CronTrigger cronTrigger = new CronTrigger(triggerConfig.getCronExpression());
        ScheduledFuture<?> scheduledFuture = this.executors.schedule(task, cronTrigger);
        this.scheduledFutureCache.compute(triggerConfig.getTriggerId(),(k,v)->{
            if (v!= null){
                v.cancel(true);
            }
            return scheduledFuture;
        });
        return triggerConfig;
    }

    @Override
    public boolean removeTrigger(String flowId, Set<String> stageIds) {
        TriggerId key = TriggerId.of(flowId, stageIds);
        if(this.scheduledFutureCache.containsKey(key)){
            ScheduledFuture<?> removedItem = this.scheduledFutureCache.remove(key);
            removedItem.cancel(true);
            return removedItem != null;
        }
        return false;
    }
}
