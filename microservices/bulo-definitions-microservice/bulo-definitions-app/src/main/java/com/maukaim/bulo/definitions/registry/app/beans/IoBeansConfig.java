package com.maukaim.bulo.definitions.registry.app.beans;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.definitions.data.lifecycle.TechnicalStageDefinitionStoreImpl;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionDeclarationEventConsumer;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.definitions.registry.app.io.TechnicalStageDefinitionDeclarationEventConsumerImpl;
import com.maukaim.bulo.definitions.registry.app.io.TechnicalStageDefinitionEventConsumerImpl;
import com.maukaim.bulo.definitions.registry.app.io.TechnicalStageDefinitionEventPublisherImpl;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.ms.connectivity.SystemConnector;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IoBeansConfig {
    @Bean
    public TechnicalStageDefinitionEventPublisher technicalStageDefinitionPublisher(SystemConnector systemConnector) {
        return new TechnicalStageDefinitionEventPublisherImpl(systemConnector);
    }

    @Bean
    public SystemConnector systemConnector(Jackson2ObjectMapperBuilderCustomizer customizer){
        return new SystemConnector(getRestTemplate(customizer));
    }

    private RestTemplate getRestTemplate(Jackson2ObjectMapperBuilderCustomizer customizer){
        Jackson2ObjectMapperBuilder objectMapperBuilder = Jackson2ObjectMapperBuilder.json();
        customizer.customize(objectMapperBuilder);
        ObjectMapper objectMapper = objectMapperBuilder.build();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, converter);
        return restTemplate;
    }

    @Bean
    public TechnicalStageDefinitionEventConsumer technicalStageDefinitionConsumer(TechnicalStageDefinitionStoreImpl technicalStageDefinitionStore,
                                                                                  TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter) {
        return new TechnicalStageDefinitionEventConsumerImpl(technicalStageDefinitionStore, technicalStageDefinitionAdapter);
    }

    @Bean
    public TechnicalStageDefinitionDeclarationEventConsumer technicalStageDefinitionDeclarationEventConsumer(TechnicalStageDefinitionService technicalStageDefinitionService,
                                                                                                             TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter) {
        return new TechnicalStageDefinitionDeclarationEventConsumerImpl(technicalStageDefinitionService, technicalStageDefinitionAdapter);
    }
}
