package com.maukaim.bulo.executors.app.beans;

import com.maukaim.bulo.executors.app.io.*;
import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.lifecycle.StageRunResultStoreImpl;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunDependencyAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunResultAdapter;
import com.maukaim.bulo.executors.io.*;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoBeansConfig {

    @Bean
    public StageDefinitionCreateInstructionPublisher stageDefinitionDeclarationEventPublisher(SystemConnector systemConnector) {
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
