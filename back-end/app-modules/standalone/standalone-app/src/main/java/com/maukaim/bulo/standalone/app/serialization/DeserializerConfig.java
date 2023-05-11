package com.maukaim.bulo.standalone.app.serialization;

import com.maukaim.bulo.serialization.data.types.DataTypeMixInsConfig;
import com.maukaim.bulo.serialization.definitions.client.DefinitionClientMixinsConfig;
import com.maukaim.bulo.serialization.flows.client.FlowClientMixinsConfig;
import com.maukaim.bulo.serialization.shared.CommonMixinsConfig;
import com.maukaim.bulo.serialization.stages.client.StageClientMixinsConfig;
import com.maukaim.bulo.trigger.serialization.scheduler.client.SchedulerMixinsConfig;
import com.maukaim.bulo.serialization.triggers.system.TriggersMixinsConfig;
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
            jacksonObjectMapperBuilder.mixIns(StageClientMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(FlowClientMixinsConfig.FLOW_CLIENT_SERIALIZATION_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(TriggersMixinsConfig.TRIGGERS_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(SchedulerMixinsConfig.TRIGGER_SCHEDULER_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(DefinitionClientMixinsConfig.DEFINITIONS_CLIENT_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(DataTypeMixInsConfig.DATA_TYPES_COMMONS_MIXINS);
        };
    }
}
