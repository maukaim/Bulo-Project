package com.maukaim.bulo.trigger.scheduler;

import com.maukaim.bulo.trigger.core.DummyTriggerEventPublisher;
import com.maukaim.bulo.trigger.core.TriggerEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TriggerConfig {

    @Bean
    public TriggerEventPublisher getPublisher(){
        return new DummyTriggerEventPublisher();
    }
}
