package com.maukaim.bulo.trigger.scheduler.app.beans;


import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.trigger.scheduler.app.io.ScheduleTriggerConsumerImpl;
import com.maukaim.bulo.trigger.scheduler.app.io.TriggerEventPublisherImpl;
import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConsumer;
import com.maukaim.bulo.triggers.io.TriggerEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoBeansConfig {
    @Bean
    public TriggerEventPublisher getPublisher(SystemConnector systemConnector) {
        return new TriggerEventPublisherImpl(systemConnector);
    }

    @Bean
    public ScheduleTriggerConsumer scheduleTriggerConsumer(ScheduleTriggerService service) {
        return new ScheduleTriggerConsumerImpl(service);
    }

}
