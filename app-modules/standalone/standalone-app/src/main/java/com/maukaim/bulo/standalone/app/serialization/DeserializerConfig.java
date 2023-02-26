package com.maukaim.bulo.standalone.app.serialization;

import com.maukaim.bulo.commons.serialization.CommonMixinsConfig;
import com.maukaim.bulo.definitions.registry.serialization.DefinitionMixinsConfig;
import com.maukaim.bulo.flows.serialization.FlowMixinsConfig;
import com.maukaim.bulo.serialization.data.types.DataTypeMixInsConfig;
import com.maukaim.bulo.serialization.stage.StageMixinsConfig;
import com.maukaim.bulo.trigger.scheduler.serialization.SchedulerMixinsConfig;
import com.maukaim.bulo.triggers.serialization.TriggersMixinsConfig;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.maukaim.bulo.serialization.data.types.DataTypeMixInsConfig.COMMON_DESERIALIZERS;

@Configuration
public class DeserializerConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.deserializersByType(COMMON_DESERIALIZERS);
            jacksonObjectMapperBuilder.mixIns(CommonMixinsConfig.COMMON_SERIALIZATION_JACKSON_MIXINS);
            jacksonObjectMapperBuilder.mixIns(StageMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(FlowMixinsConfig.SERIALIZATION_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(TriggersMixinsConfig.TRIGGERS_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(SchedulerMixinsConfig.TRIGGER_SCHEDULER_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(DefinitionMixinsConfig.DEFINITIONS_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(DataTypeMixInsConfig.DATA_TYPES_COMMONS_MIXINS);
        };
    }
}
