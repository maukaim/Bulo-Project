package com.maukaim.bulo.trigger.scheduler.app.beans;

import com.maukaim.bulo.triggers.io.TriggerEventPublisher;
import com.maukaim.bulo.triggers.scheduler.data.TriggerConnector;
import com.maukaim.bulo.scheduler.ms.data.lifecycle.TriggerConnectorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBeansConfig {
    @Bean
    public TriggerConnector triggerConnector(TriggerEventPublisher triggerEventPublisher) {
        return new TriggerConnectorImpl(triggerEventPublisher);
    }
}
