package com.maukaim.bulo.standalone.app.beans.modules;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.standalone.app.connectivity.TriggerConnectorImpl;
import com.maukaim.bulo.trigger.scheduler.core.CronExecutorService;
import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.cron.spring.engine.SpringCronExecutorServiceImpl;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class TriggerModuleBeansConfig {
    @Bean
    public CronExecutorService cronExecutorService(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.initialize();
        threadPoolTaskScheduler.setPoolSize(4);
        threadPoolTaskScheduler.setThreadNamePrefix("ScheduleTriggerService-standalone-pool-thread-d%");
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        threadPoolTaskScheduler.setErrorHandler(throwable -> {
            System.out.println("While executing task -> " + throwable.getMessage());
        });
        return new SpringCronExecutorServiceImpl(threadPoolTaskScheduler);
    }

    @Bean
    public ScheduleTriggerService scheduleTriggerService(CronExecutorService executorService,
                                                         TriggerConnector triggerConnector
                                                         ){
        return new ScheduleTriggerService(executorService,triggerConnector);
    }

    @Bean
    public TriggerConnector triggerConnector(FlowRunService flowRunService){
        return new TriggerConnectorImpl(flowRunService);
    }
}
