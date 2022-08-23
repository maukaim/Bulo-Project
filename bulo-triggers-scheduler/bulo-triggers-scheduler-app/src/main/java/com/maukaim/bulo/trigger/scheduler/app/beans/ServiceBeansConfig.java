package com.maukaim.bulo.trigger.scheduler.app.beans;

import com.maukaim.bulo.trigger.scheduler.core.CronExecutorService;
import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.cron.spring.engine.SpringCronExecutorServiceImpl;
import com.maukaim.bulo.triggers.core.TriggerEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


@Configuration
public class ServiceBeansConfig {

    @Bean
    public ScheduleTriggerService getTriggerService(TriggerEventPublisher triggerEventPublisher,
                                                    CronExecutorService cronExecutorService) {
        return new ScheduleTriggerService(cronExecutorService, triggerEventPublisher);
    }

    @Bean
    public CronExecutorService getCronExecutorService() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.initialize();
        threadPoolTaskScheduler.setPoolSize(4);
        threadPoolTaskScheduler.setThreadNamePrefix("ScheduleTriggerService-schedule-pool-thread-d%");
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        threadPoolTaskScheduler.setErrorHandler(throwable -> {
            System.out.println("While executing task -> " + throwable.getMessage());
        });
        return new SpringCronExecutorServiceImpl(threadPoolTaskScheduler);
    }
}
