package com.maukaim.bulo.ms.shared.spring.servers.autoconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.app.shared.servers.DefaultApplicationMarshaller;
import com.maukaim.bulo.marshalling.Marshaller;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.List;

@Configuration
@AutoConfigureBefore(SystemConnectorConfig.class)
@AutoConfigureAfter(Jackson2ObjectMapperBuilderCustomizer.class)
public class ApplicationMarshallerConfig {
    @Bean
    @ConditionalOnBean({Jackson2ObjectMapperBuilderCustomizer.class})
    public Marshaller marshaller(List<Jackson2ObjectMapperBuilderCustomizer> customizers) {
        ObjectMapper objectMapper = getObjectMapper(customizers);
        return new DefaultApplicationMarshaller(objectMapper);
    }

    private ObjectMapper getObjectMapper(List<Jackson2ObjectMapperBuilderCustomizer> customizers) {
        Jackson2ObjectMapperBuilder objectMapperBuilder = Jackson2ObjectMapperBuilder.json();
        customizers.forEach(customizer -> customizer.customize(objectMapperBuilder));
        return objectMapperBuilder.build();
    }
}
