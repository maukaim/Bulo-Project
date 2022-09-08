package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.data.lifecycle.TechnicalStageDefinitionStoreImpl;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionDeclarationEventConsumer;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.definitions.registry.app.io.TechnicalStageDefinitionDeclarationEventConsumerImpl;
import com.maukaim.bulo.definitions.registry.app.io.TechnicalStageDefinitionEventConsumerImpl;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoBeansConfig {
    @Bean
    public TechnicalStageDefinitionEventPublisher technicalStageDefinitionPublisher() {
        return (event) -> {
            System.out.println(String.format("Publish to Kafka :::>> event %s, on TechnicalStageDefinition %s", event.getEventType(), event.getTechnicalStageDefinition()));
            return false;
        };
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
