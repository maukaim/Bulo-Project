package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.stages.app.io.*;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.io.*;
import com.maukaim.bulo.stages.persistence.adapters.StageAdapter;
import com.maukaim.bulo.stages.persistence.StageStoreImpl;
import com.maukaim.bulo.stages.persistence.adapters.TechnicalStageDefinitionAdapter;
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
    public StageUpdateEventPublisher stageUpdateEventPublisher() {
        return new DummyStageUpdateEventPublisher();
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
    public TechnicalStageDefinitionEventConsumer technicalStageDefinitionEventConsumer(TechnicalStageDefinitionService definitionService,
                                                                                       TechnicalStageDefinitionAdapter definitionAdapter){
        return new TechnicalStageDefinitionEventConsumerImpl(definitionAdapter,definitionService);
    }

}
