package com.maukaim.bulo.standalone.app.serialization;

import com.maukaim.bulo.commons.serialization.CommonMixinsConfig;
import com.maukaim.bulo.definitions.registry.serialization.DefinitionMixinsConfig;
import com.maukaim.bulo.flows.serialization.FlowMixinsConfig;
import com.maukaim.bulo.serialization.StageMixinsConfig;
import com.maukaim.bulo.trigger.scheduler.serialization.SchedulerMixinsConfig;
import com.maukaim.bulo.triggers.serialization.TriggersMixinsConfig;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeserializerConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.mixIns(CommonMixinsConfig.COMMON_SERIALIZATION_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(StageMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(FlowMixinsConfig.FLOWS_SERIALIZATION_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(TriggersMixinsConfig.TRIGGERS_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(SchedulerMixinsConfig.TRIGGER_SCHEDULER_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(DefinitionMixinsConfig.DEFINITIONS_REGISTRY_JACKSON_MIXIN);
        };
    }
}