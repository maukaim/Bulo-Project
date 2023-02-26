package com.maukaim.bulo.executors.app.deserializer;

import com.maukaim.bulo.serialization.shared.CommonMixinsConfig;
import com.maukaim.bulo.serialization.executors.system.ExecutorMixinsConfig;
import com.maukaim.bulo.serialization.runs.orchestrators.system.OrchestratorMixinsConfig;
import com.maukaim.bulo.serialization.stages.system.StageSystemMixinsConfig;
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
            jacksonObjectMapperBuilder.mixIns(ExecutorMixinsConfig.EXECUTORS_SERVICE_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(OrchestratorMixinsConfig.ORCHESTRATOR_SERIALIZATION_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(StageSystemMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
        };
    }
}
