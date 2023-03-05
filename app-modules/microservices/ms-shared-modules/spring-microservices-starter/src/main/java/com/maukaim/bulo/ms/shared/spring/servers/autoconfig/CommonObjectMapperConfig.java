package com.maukaim.bulo.ms.shared.spring.servers.autoconfig;

import com.fasterxml.jackson.databind.MapperFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class CommonObjectMapperConfig {
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 1)
    public Jackson2ObjectMapperBuilderCustomizer commonCustomizer() {
        return builder -> {
            builder.failOnEmptyBeans(false);
            builder.failOnUnknownProperties(false);
            builder.featuresToEnable(MapperFeature.AUTO_DETECT_GETTERS, MapperFeature.AUTO_DETECT_IS_GETTERS);
        };
    }
}
