package com.maukaim.bulo.trigger.scheduler.app.beans;


import com.maukaim.bulo.ms.connectivity.SystemConnector;
import com.maukaim.bulo.trigger.scheduler.app.io.ScheduleTriggerConsumerImpl;
import com.maukaim.bulo.trigger.scheduler.app.io.TriggerEventPublisherImpl;
import com.maukaim.bulo.trigger.scheduler.core.ScheduleTriggerService;
import com.maukaim.bulo.trigger.scheduler.io.ScheduleTriggerConsumer;
import com.maukaim.bulo.triggers.io.TriggerEventPublisher;
import com.maukaim.bulo.triggers.scheduler.data.lifecycle.ScheduleTriggerConfigAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IoBeansConfig {

    @Bean
    public SystemConnector systemConnector(){
        return new SystemConnector(new RestTemplate());
    }

    @Bean
    public TriggerEventPublisher getPublisher(SystemConnector systemConnector){
        return new TriggerEventPublisherImpl(systemConnector);
    }

    @Bean
    public ScheduleTriggerConsumer scheduleTriggerConsumer(ScheduleTriggerConfigAdapter configAdapter,
                                                           ScheduleTriggerService service){
        return new ScheduleTriggerConsumerImpl(configAdapter, service);
    }

}
