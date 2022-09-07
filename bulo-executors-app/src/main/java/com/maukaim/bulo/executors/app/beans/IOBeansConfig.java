package com.maukaim.bulo.executors.app.beans;

import com.maukaim.bulo.executors.app.io.*;
import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.executors.data.StageRunResultStore;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.lifecycle.StageRunResultStoreImpl;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunDependencyAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunResultAdapter;
import com.maukaim.bulo.executors.io.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOBeansConfig {

    @Bean
    public StageDefinitionDeclarationEventPublisher technicalStageDefinitionDeclarationEventPublisher() {
        return new DummyStageDefinitionDeclarationEventPublisher();
    }

    @Bean
    public StageRunEventPublisher stageRunEventPublisher() {
        return new DummyStageRunEventPublisher();
    }

    @Bean
    public StageRunResultEventPublisher stageRunResultEventPublisher() {
        return new StageRunResultEventPublisherImpl();
    }

    @Bean
    public NeedStageRunEventConsumer needStageRunEventConsumer(StageRunEventProcessor stageRunEventProcessor,
                                                               StageRunDependencyAdapter stageRunDependencyAdapter) {
        return new NeedStageRunEventConsumerImpl(stageRunEventProcessor, stageRunDependencyAdapter);
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
