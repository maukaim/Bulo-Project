package com.maukaim.bulo.executors.app.deserializer;

import com.maukaim.bulo.commons.serialization.CommonMixinsConfig;
import com.maukaim.bulo.executors.serialization.ExecutorMixinsConfig;
import com.maukaim.bulo.runs.orchestrators.serialization.OrchestratorMixinsConfig;
import com.maukaim.bulo.serialization.stage.StageMixinsConfig;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.maukaim.bulo.commons.serialization.CommonMixinsConfig.COMMON_DESERIALIZERS;

@Configuration
public class DeserializerConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.deserializersByType(COMMON_DESERIALIZERS);
            jacksonObjectMapperBuilder.mixIns(CommonMixinsConfig.COMMON_SERIALIZATION_JACKSON_MIXINS);
            jacksonObjectMapperBuilder.mixIns(ExecutorMixinsConfig.EXECUTORS_SERVICE_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(OrchestratorMixinsConfig.ORCHESTRATOR_SERIALIZATION_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(StageMixinsConfig.STAGES_SERVICE_JACKSON_MIXIN);
        };
    }
}
