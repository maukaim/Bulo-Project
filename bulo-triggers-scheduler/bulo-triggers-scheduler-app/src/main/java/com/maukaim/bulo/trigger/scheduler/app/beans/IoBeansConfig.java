package com.maukaim.bulo.trigger.scheduler.app.beans;


import com.maukaim.bulo.trigger.scheduler.app.io.ScheduleTriggerConsumerImpl;
import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConsumer;
import com.maukaim.bulo.triggers.io.DummyTriggerEventPublisher;
import com.maukaim.bulo.triggers.io.TriggerEventPublisher;
import com.maukaim.bulo.triggers.scheduler.data.lifecycle.ScheduleTriggerConfigAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoBeansConfig {

    @Bean
    public TriggerEventPublisher getPublisher(){
        return new DummyTriggerEventPublisher();
    }

    @Bean
    public ScheduleTriggerConsumer scheduleTriggerConsumer(ScheduleTriggerConfigAdapter configAdapter,
                                                           ScheduleTriggerService service){
        return new ScheduleTriggerConsumerImpl(configAdapter, service);
    }

}
