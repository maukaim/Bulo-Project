package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.io.stages.client.CreateStageEventConsumer;
import com.maukaim.bulo.io.stages.client.DeleteStageEventConsumer;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageUpdateEventPublisher;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageDefinitionEventConsumer;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.stages.app.io.*;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.stages.data.lifecycle.StageAdapter;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageStoreImpl;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.StageDefinitionAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOBeansConfig {

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(StageStoreImpl stageStore,
                                                             StageAdapter stageAdapter) {
        return new StageUpdateEventConsumerImpl(stageStore, stageAdapter);
    }

    @Bean
    public StageUpdateEventPublisher stageUpdateEventPublisher(SystemConnector<ServiceEventType> systemConnector) {
        return new StageUpdateEventPublisherImpl(systemConnector);
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
    public StageDefinitionEventConsumer technicalStageDefinitionEventConsumer(StageDefinitionService definitionService,
                                                                              StageDefinitionAdapter definitionAdapter){
        return new StageDefinitionEventConsumerImpl(definitionAdapter,definitionService);
    }

}
