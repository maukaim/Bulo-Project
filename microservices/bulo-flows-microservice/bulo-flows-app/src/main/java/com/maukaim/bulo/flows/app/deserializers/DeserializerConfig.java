package com.maukaim.bulo.flows.app.deserializers;


import com.maukaim.bulo.commons.serialization.CommonMixinsConfig;
import com.maukaim.bulo.flows.serialization.MixinsConfig;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeserializerConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.autoDetectGettersSetters(false);
            jacksonObjectMapperBuilder.mixIns(CommonMixinsConfig.COMMON_SERIALIZATION_JACKSON_MIXIN);
            jacksonObjectMapperBuilder.mixIns(MixinsConfig.SERIALIZATION_JACKSON_MIXIN);
        };
    }
}