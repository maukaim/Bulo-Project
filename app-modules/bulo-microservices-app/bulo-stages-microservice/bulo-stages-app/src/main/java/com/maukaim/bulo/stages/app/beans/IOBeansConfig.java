package com.maukaim.bulo.stages.app.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.ms.connectivity.SystemConnector;
import com.maukaim.bulo.stages.app.io.*;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.stages.io.*;
import com.maukaim.bulo.stages.data.lifecycle.StageAdapter;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageStoreImpl;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IOBeansConfig {

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(StageStoreImpl stageStore,
                                                             StageAdapter stageAdapter) {
        return new StageUpdateEventConsumerImpl(stageStore, stageAdapter);
    }

    @Bean
    public StageUpdateEventPublisher stageUpdateEventPublisher(SystemConnector systemConnector) {
        return new StageUpdateEventPublisherImpl(systemConnector);
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
    public CreateStageEventConsumer createStageEventConsumer(StageService stageService,
                                                             StageAdapter stageAdapter
                                                             ){
        return new CreateStageEventConsumerImpl(stageService, stageAdapter);
    }

    @Bean
    public DeleteStageEventConsumer deleteStageEventConsumer(StageService stageService){
        return new DeleteStageEventConsumerImpl(stageService);
    }

    @Bean
    public TechnicalStageDefinitionEventConsumer technicalStageDefinitionEventConsumer(StageDefinitionService definitionService,
                                                                                       TechnicalStageDefinitionAdapter definitionAdapter){
        return new TechnicalStageDefinitionEventConsumerImpl(definitionAdapter,definitionService);
    }

}
