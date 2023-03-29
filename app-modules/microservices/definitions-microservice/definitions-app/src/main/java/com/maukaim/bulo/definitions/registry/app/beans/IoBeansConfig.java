package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.data.lifecycle.definitions.client.CreateStageDefinitionConsumer;
import com.maukaim.bulo.data.lifecycle.definitions.client.StageDefinitionAdapter;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.ms.data.lifecycle.StageDefinitionStoreImpl;
import com.maukaim.bulo.definitions.ms.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.definitions.ms.data.lifecycle.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.definitions.ms.data.lifecycle.TechnicalStageDefinitionEventPublisher;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.definitions.registry.app.io.CreateStageDefinitionConsumerImpl;
import com.maukaim.bulo.definitions.registry.app.io.StageUpdateEventConsumerImpl;
import com.maukaim.bulo.definitions.registry.app.io.TechnicalStageDefinitionEventConsumerImpl;
import com.maukaim.bulo.definitions.registry.app.io.TechnicalStageDefinitionEventPublisherImpl;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumer;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumerFactory;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaUtil;
import com.maukaim.bulo.shared.server.core.SystemContext;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoBeansConfig {
    @Bean
    public TechnicalStageDefinitionEventPublisher technicalStageDefinitionPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
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

    @ConditionalOnProperty(prefix = "bulo.communication", value = "mode", havingValue = "compound")
    public class KafkaConsumerConfig {
        @Bean
        public KafkaConsumer<StageDefinitionEvent> getKafkaDefinitionConsumer(SystemContext systemContext,
                                                                              KafkaConsumerFactory kafkaConsumerFactory,
                                                                              TechnicalStageDefinitionEventConsumer technicalStageDefinitionConsumer) {
            return kafkaConsumerFactory.create(MicroServiceEventType.DEF_UPDATE,
                    StageDefinitionEvent.class,
                    (e) -> technicalStageDefinitionConsumer.consume(e),
                    OffsetResetStrategy.LATEST,
                    KafkaUtil.toGroupId(systemContext.getServiceName())
            );
        }
    }
}