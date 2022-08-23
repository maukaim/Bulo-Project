package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.registry.io.TechnicalStageDefinitionPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherBeanConfig {
    @Bean
    public TechnicalStageDefinitionPublisher technicalStageDefinitionPublisher() {
        return (eventType, record) -> System.out.println(String.format("Publish to Kafka :::>> event %s, on TechnicalStageDefinition %s", eventType.name(), record));
    }
}
