package com.maukaim.bulo.trigger.scheduler.cron.spring.engine;

import com.maukaim.bulo.trigger.scheduler.core.CronExecutorService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

public class SpringCronExecutorServiceImpl implements CronExecutorService {
    private final ThreadPoolTaskScheduler executors;

    public SpringCronExecutorServiceImpl(ThreadPoolTaskScheduler threadPoolTaskScheduler){
        this.executors = threadPoolTaskScheduler;
    }
    @Override
    public ScheduledFuture<?> schedule(Runnable task, String cronExpression) {
        CronTrigger cronTrigger = new CronTrigger(cronExpression);
        return this.executors.schedule(task, cronTrigger);
    }
}
