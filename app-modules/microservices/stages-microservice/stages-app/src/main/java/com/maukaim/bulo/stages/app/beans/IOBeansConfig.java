package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.io.definitions.system.ExecutorUpdateEvent;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.io.stages.client.CreateStageEventConsumer;
import com.maukaim.bulo.io.stages.client.DeleteStageEventConsumer;
import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.ms.shared.spring.servers.KafkaConsumerProvider;
import com.maukaim.bulo.ms.shared.spring.servers.autoconfig.conditions.KafkaActivatedCondition;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumer;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumerFactory;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaUtil;
import com.maukaim.bulo.shared.server.core.SystemContext;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageUpdateEventPublisher;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageDefinitionEventConsumer;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.stages.app.io.*;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.data.lifecycle.stages.client.StageAdapter;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageStoreImpl;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.StageDefinitionAdapter;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class IOBeansConfig {

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(StageStoreImpl stageStore,
                                                             StageAdapter stageAdapter) {
        return new StageUpdateEventConsumerImpl(stageStore, stageAdapter);
    }

    @Bean
    public StageUpdateEventPublisher stageUpdateEventPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
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

    @Conditional(KafkaActivatedCondition.class)
    public class KafkaConsumerConfig extends KafkaConsumerProvider {
        private SystemContext systemContext;
        private StageDefinitionEventConsumer stageDefinitionEventConsumer;
        private StageUpdateEventConsumer stageUpdateEventConsumer;

        @Autowired
        public KafkaConsumerConfig(
                StageUpdateEventConsumer stageUpdateEventConsumer,
                StageDefinitionEventConsumer stageDefinitionEventConsumer,
                SystemContext systemContext,
                KafkaConsumerFactory kafkaConsumerFactory) {
            super(kafkaConsumerFactory);
            this.systemContext = systemContext;
            this.stageUpdateEventConsumer = stageUpdateEventConsumer;
            this.stageDefinitionEventConsumer = stageDefinitionEventConsumer;
        }

        @Bean
        @Override
        public List<KafkaConsumer<?>> getConsumers() {
            return List.of(
                    kafkaConsumerFactory.create(MicroServiceEventType.DEF_UPDATE,
                            StageDefinitionEvent.class,
                            (e) -> stageDefinitionEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName())),

                    kafkaConsumerFactory.create(MicroServiceEventType.STAGE_UPDATE,
                            StageUpdateEvent.class,
                            (e) -> stageUpdateEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName()))
            );
        }
    }
}
