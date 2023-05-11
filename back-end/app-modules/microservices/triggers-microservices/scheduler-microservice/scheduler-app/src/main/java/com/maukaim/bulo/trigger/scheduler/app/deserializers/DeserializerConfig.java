package com.maukaim.bulo.trigger.scheduler.app.deserializers;

import com.maukaim.bulo.serialization.shared.CommonMixinsConfig;
import com.maukaim.bulo.serialization.triggers.system.TriggersMixinsConfig;
import com.maukaim.bulo.trigger.serialization.scheduler.client.SchedulerMixinsConfig;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeserializerConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.mixIns(CommonMixinsConfig.COMMON_SERIALIZATION_JACKSON_MIXINS);
            jacksonObjectMapperBuilder.mixIns(TriggersMixinsConfig.TRIGGERS_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(SchedulerMixinsConfig.TRIGGER_SCHEDULER_JACKSON_MIXIN);
        };
    }
}
