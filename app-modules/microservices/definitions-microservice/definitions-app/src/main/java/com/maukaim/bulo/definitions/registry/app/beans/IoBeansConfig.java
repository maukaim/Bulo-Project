package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionAdapter;
import com.maukaim.bulo.io.definitions.system.CreateStageDefinitionConsumer;
import com.maukaim.bulo.io.definitions.system.StageUpdateEventConsumer;
import com.maukaim.bulo.io.definitions.system.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.io.definitions.system.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.definitions.ms.data.lifecycle.StageDefinitionStoreImpl;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.stages.StageAdapter;
import com.maukaim.bulo.definitions.registry.app.io.CreateStageDefinitionConsumerImpl;
import com.maukaim.bulo.definitions.registry.app.io.StageUpdateEventConsumerImpl;
import com.maukaim.bulo.definitions.registry.app.io.TechnicalStageDefinitionEventConsumerImpl;
import com.maukaim.bulo.definitions.registry.app.io.TechnicalStageDefinitionEventPublisherImpl;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoBeansConfig {
    @Bean
    public TechnicalStageDefinitionEventPublisher technicalStageDefinitionPublisher(SystemConnector<ServiceEventType> systemConnector) {
        return new TechnicalStageDefinitionEventPublisherImpl(systemConnector);
    }

    @Bean
    public TechnicalStageDefinitionEventConsumer technicalStageDefinitionConsumer(StageDefinitionStoreImpl technicalStageDefinitionStore,
                                                                                  StageDefinitionAdapter stageDefinitionAdapter) {
        return new TechnicalStageDefinitionEventConsumerImpl(technicalStageDefinitionStore, stageDefinitionAdapter);
    }

    @Bean
    public CreateStageDefinitionConsumer technicalStageDefinitionDeclarationEventConsumer(StageDefinitionService stageDefinitionService,
                                                                                          StageDefinitionAdapter stageDefinitionAdapter) {
        return new CreateStageDefinitionConsumerImpl(stageDefinitionService, stageDefinitionAdapter);
    }

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(StageStore stageStore, StageAdapter stageAdapter) {
        return new StageUpdateEventConsumerImpl(stageStore, stageAdapter);
    }
}
