package com.maukaim.bulo.executors.app.beans;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.executors.app.io.DummyStageDefinitionCreateInstructionPublisher;
import com.maukaim.bulo.executors.app.io.DummyStageRunEventPublisher;
import com.maukaim.bulo.executors.app.io.NeedStageRunCancelEventConsumerImpl;
import com.maukaim.bulo.executors.app.io.NeedStageRunEventConsumerImpl;
import com.maukaim.bulo.executors.app.io.StageRunResultEventConsumerImpl;
import com.maukaim.bulo.executors.app.io.StageRunResultEventPublisherImpl;
import com.maukaim.bulo.executors.app.io.StageUpdateEventConsumerImpl;
import com.maukaim.bulo.executors.core.StageRunProcessor;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.lifecycle.NeedStageRunCancelEventConsumer;
import com.maukaim.bulo.executors.data.lifecycle.NeedStageRunEventConsumer;
import com.maukaim.bulo.executors.data.lifecycle.StageDefinitionCreateInstructionPublisher;
import com.maukaim.bulo.executors.data.lifecycle.StageRunEventPublisher;
import com.maukaim.bulo.executors.data.lifecycle.StageRunResultEventConsumer;
import com.maukaim.bulo.executors.data.lifecycle.StageRunResultEventPublisher;
import com.maukaim.bulo.executors.data.lifecycle.StageRunResultStoreImpl;
import com.maukaim.bulo.executors.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunDependencyAdapter;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunResultAdapter;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunCancellationEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.NeedStageRunExecutionEvent;
import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.ms.shared.spring.servers.KafkaConsumerProvider;
import com.maukaim.bulo.ms.shared.spring.servers.autoconfig.conditions.KafkaActivatedCondition;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumer;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumerFactory;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaUtil;
import com.maukaim.bulo.shared.server.core.SystemContext;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class IoBeansConfig {

    @Bean
    public StageDefinitionCreateInstructionPublisher stageDefinitionDeclarationEventPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
        return new DummyStageDefinitionCreateInstructionPublisher(systemConnector);
    }

    @Bean
    public StageRunEventPublisher stageRunEventPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
        return new DummyStageRunEventPublisher(systemConnector);
    }

    @Bean
    public StageRunResultEventPublisher stageRunResultEventPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
        return new StageRunResultEventPublisherImpl(systemConnector);
    }

    @Bean
    public NeedStageRunEventConsumer needStageRunEventConsumer(StageRunProcessor stageRunProcessor,
                                                               StageRunDependencyAdapter stageRunDependencyAdapter) {
        return new NeedStageRunEventConsumerImpl(stageRunProcessor, stageRunDependencyAdapter);
    }

    @Bean
    public NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer(StageRunProcessor stageRunProcessor) {
        return new NeedStageRunCancelEventConsumerImpl(stageRunProcessor);
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

    @Conditional(KafkaActivatedCondition.class)
    public class KafkaConsumerConfig extends KafkaConsumerProvider {
        private SystemContext systemContext;
        private NeedStageRunEventConsumer needStageRunEventConsumer;
        private NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer;
        private StageUpdateEventConsumer stageUpdateEventConsumer;

        @Autowired
        public KafkaConsumerConfig(
                SystemContext systemContext,
                StageUpdateEventConsumer stageUpdateEventConsumer,
                NeedStageRunEventConsumer needStageRunEventConsumer,
                NeedStageRunCancelEventConsumer needStageRunCancelEventConsumer,
                KafkaConsumerFactory kafkaConsumerFactory) {
            super(kafkaConsumerFactory);
            this.systemContext = systemContext;
            this.stageUpdateEventConsumer = stageUpdateEventConsumer;
            this.needStageRunEventConsumer = needStageRunEventConsumer;
            this.needStageRunCancelEventConsumer = needStageRunCancelEventConsumer;
        }

        @Bean
        @Override
        public List<KafkaConsumer<?>> getConsumers() {
            return List.of(
                    kafkaConsumerFactory.create(MicroServiceEventType.STAGE_UPDATE,
                            StageUpdateEvent.class,
                            (e) -> stageUpdateEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName())),

                    kafkaConsumerFactory.create(MicroServiceEventType.STAGE_RUN_READY_TO_START_EVENT,
                            NeedStageRunExecutionEvent.class,
                            (e) -> needStageRunEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName())),

                    kafkaConsumerFactory.create(MicroServiceEventType.NEED_STAGE_RUN_CANCEL,
                            NeedStageRunCancellationEvent.class,
                            (e) -> needStageRunCancelEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName()))
            );
        }
    }
}
