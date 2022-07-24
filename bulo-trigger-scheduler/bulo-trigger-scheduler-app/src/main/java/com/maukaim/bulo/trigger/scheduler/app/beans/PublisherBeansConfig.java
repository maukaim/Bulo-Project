package com.maukaim.bulo.trigger.scheduler.app.beans;

import com.maukaim.bulo.triggers.core.DummyTriggerEventPublisher;
import com.maukaim.bulo.triggers.core.TriggerEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherBeansConfig {

    @Bean
    public TriggerEventPublisher getPublisher(){
        return new DummyTriggerEventPublisher();
    }

}
