package com.maukaim.boule.trigger.scheduler;

import com.maukaim.boule.trigger.core.DummyTriggerEventPublisher;
import com.maukaim.boule.trigger.core.TriggerEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TriggerConfig {

    @Bean
    public TriggerEventPublisher getPublisher(){
        return new DummyTriggerEventPublisher();
    }
}
