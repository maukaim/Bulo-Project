package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.stages.app.io.DirectStageUpdateEventPublisherImpl;
import com.maukaim.bulo.io.StageUpdateEventConsumer;
import com.maukaim.bulo.stages.app.io.StageUpdateEventConsumerImpl;
import com.maukaim.bulo.stages.core.StageAdapter;
import com.maukaim.bulo.stages.persistence.StageStoreImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOBeansConfig {

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(StageStoreImpl stageStore,
                                                             StageAdapter stageAdapter,
                                                             DirectStageUpdateEventPublisherImpl publisher){
        StageUpdateEventConsumerImpl consumer = new StageUpdateEventConsumerImpl(stageStore, stageAdapter);
        publisher.setConsumer(consumer);
        return consumer;
    }

    @Bean
    public DirectStageUpdateEventPublisherImpl stageUpdateEventPublisher(){
        return new DirectStageUpdateEventPublisherImpl();
    }
}
