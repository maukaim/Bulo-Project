package com.maukaim.bulo.trigger.scheduler.app.beans;

import com.maukaim.bulo.triggers.scheduler.data.lifecycle.ScheduleTriggerConfigAdapter;
import com.maukaim.bulo.triggers.scheduler.data.lifecycle.ScheduleTriggerConfigAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterBeansConfig {

    @Bean
    public ScheduleTriggerConfigAdapter configAdapter(){
        return new ScheduleTriggerConfigAdapterImpl();
    }
}
