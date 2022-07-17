package com.maukaim.bulo.runs.orchestrator.app.deserializers;

import com.maukaim.bulo.runs.orchestrator.serialization.MixinsConfig;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeserializerConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.mixIns(MixinsConfig.SERIALIZATION_JACKSON_MIXIN);
    }
}
