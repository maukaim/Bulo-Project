package com.maukaim.bulo.trigger.scheduler.core;

import java.util.concurrent.ScheduledFuture;

public interface CronExecutorService {
    ScheduledFuture<?> schedule(Runnable task, String cronExpression);
}
