package com.maukaim.bulo.executors.app.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.executors.app.io.*;
import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.lifecycle.StageRunResultStoreImpl;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunDependencyAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunResultAdapter;
import com.maukaim.bulo.executors.io.*;
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
    public SystemConnector systemConnector(Jackson2ObjectMapperBuilderCustomizer customizer) {
        return new SystemConnector(getRestTemplate(customizer));
    }

    private RestTemplate getRestTemplate(Jackson2ObjectMapperBuilderCustomizer customizer) {
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
    public StageDefinitionCreateInstructionPublisher technicalStageDefinitionDeclarationEventPublisher(SystemConnector systemConnector) {
        return new DummyStageDefinitionCreateInstructionPublisher(systemConnector);
    }

    @Bean
    public StageRunEventPublisher stageRunEventPublisher(SystemConnector systemConnector) {
        return new DummyStageRunEventPublisher(systemConnector);
    }

    @Bean
    public StageRunResultEventPublisher stageRunResultEventPublisher(SystemConnector systemConnector) {
        return new StageRunResultEventPublisherImpl(systemConnector);
    }

    @Bean
    public NeedStageRunEventConsumer needStageRunEventConsumer(StageRunEventProcessor stageRunEventProcessor,
                                                               StageRunDependencyAdapter stageRunDependencyAdapter) {
        return new NeedStageRunEventConsumerImpl(stageRunEventProcessor, stageRunDependencyAdapter);
    }

    @Bean
    public NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer(StageRunEventProcessor stageRunEventProcessor) {
        return new NeedStageRunCancelEventConsumerImpl(stageRunEventProcessor);
    }

    @Bean
    public StageRunResultEventConsumer stageRunResultEventConsumer(StageRunResultStoreImpl stageRunResultStore,
                                                                   StageRunResultAdapter stageRunResultAdapter) {
        return new StageRunResultEventConsumerImpl(stageRunResultStore, stageRunResultAdapter);
    }

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(StageStore stageStore,
                                                             StageAdapter stageAdapter) {
        return new StageUpdateEventConsumerImpl(stageStore, stageAdapter);
    }
}
