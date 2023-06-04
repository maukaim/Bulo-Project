package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.io.executors.system.StageRunEvent;
import com.maukaim.bulo.io.flows.system.FlowEvent;
import com.maukaim.bulo.io.runs.orchestrators.system.events.FlowRunEvent;
import com.maukaim.bulo.io.stages.system.StageUpdateEvent;
import com.maukaim.bulo.ms.shared.spring.servers.KafkaConsumerProvider;
import com.maukaim.bulo.ms.shared.spring.servers.autoconfig.conditions.KafkaActivatedCondition;
import com.maukaim.bulo.ms.shared.system.communication.api.MicroServiceEventType;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumer;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaConsumerFactory;
import com.maukaim.bulo.ms.shared.system.communication.kafka.KafkaUtil;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.DefinitionUpdateEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.FlowEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.FlowRunEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.StageRunEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.StageUpdateEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.TriggerEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.FlowRunEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.NeedStageRunCancellationEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.NeedStageRunExecutionEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.core.impl.AcknowledgeStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunCancelledStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunFailedStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunSuccessfulStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.StartRunStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.DefinitionUpdateEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.FlowEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.FlowRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.FlowRunEventPublisher;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.FlowRunStoreImpl;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.NeedStageRunExecutionEventPublisher;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.StageRunEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.TriggerEventConsumer;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.StageDefinitionAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.FlowAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.FlowRunAdapter;
import com.maukaim.bulo.shared.server.core.SystemContext;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class IOBeansConfig {

    @Bean
    public FlowRunEventConsumer flowRunEventConsumer(FlowRunStoreImpl flowRunStoreImpl,
                                                     FlowRunAdapter flowRunAdapter) {
        return new FlowRunEventConsumerImpl(flowRunStoreImpl, flowRunAdapter);
    }

    @Bean
    public TriggerEventConsumer triggerEventConsumer(FlowRunService flowRunService) {
        return new TriggerEventConsumerImpl(flowRunService);
    }

    @Bean
    public StageRunEventConsumer stageRunEventConsumer(AcknowledgeStageRunEventProcessor acknowledgeStageEventProcessor,
                                                       RunCancelledStageRunEventProcessor runCancelledStageEventProcessor,
                                                       RunSuccessfulStageRunEventProcessor runSuccessfulStageEventProcessor,
                                                       RunFailedStageRunEventProcessor runFailedStageEventProcessor,
                                                       StartRunStageRunEventProcessor startRunStageEventProcessor,
                                                       StageRunStore stageRunStore) {
        return new StageRunEventConsumerImpl(acknowledgeStageEventProcessor,
                runCancelledStageEventProcessor,
                runSuccessfulStageEventProcessor,
                runFailedStageEventProcessor,
                startRunStageEventProcessor,
                stageRunStore);
    }

    @Bean
    public FlowRunEventPublisher flowRunEventPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
        return new FlowRunEventPublisherImpl(systemConnector);
    }

    @Bean
    public FlowEventConsumer flowEventConsumer(FlowAdapter flowAdapter,
                                               FlowStore flowStore) {
        return new FlowEventConsumerImpl(flowAdapter, flowStore);
    }

    @Bean
    public NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
        return new NeedStageRunExecutionEventPublisherImpl(systemConnector);
    }

    @Bean
    public NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
        return new NeedStageRunCancellationEventPublisherImpl(systemConnector);
    }

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(FunctionalStageStore functionalStageStore) {
        return new StageUpdateEventConsumerImpl(functionalStageStore);
    }

    @Bean
    public DefinitionUpdateEventConsumer definitionUpdateEventConsumer(StageDefinitionAdapter stageDefinitionAdapter,
                                                                       FunctionalStageDefinitionService definitionService) {
        return new DefinitionUpdateEventConsumerImpl(stageDefinitionAdapter, definitionService);
    }

    @Conditional(KafkaActivatedCondition.class)
    public class KafkaConsumerConfig extends KafkaConsumerProvider {
        private SystemContext systemContext;
        private DefinitionUpdateEventConsumer definitionUpdateEventConsumer;
        private StageUpdateEventConsumer stageUpdateEventConsumer;
        private FlowEventConsumer flowEventConsumer;
        private FlowRunEventConsumer flowRunEventConsumer;
        private StageRunEventConsumer stageRunEventConsumer;

        @Autowired
        public KafkaConsumerConfig(
                SystemContext systemContext,
                StageUpdateEventConsumer stageUpdateEventConsumer,
                DefinitionUpdateEventConsumer definitionUpdateEventConsumer,
                FlowEventConsumer flowEventConsumer,
                FlowRunEventConsumer flowRunEventConsumer,
                StageRunEventConsumer stageRunEventConsumer,
                KafkaConsumerFactory kafkaConsumerFactory) {
            super(kafkaConsumerFactory);
            this.systemContext = systemContext;
            this.stageUpdateEventConsumer = stageUpdateEventConsumer;
            this.definitionUpdateEventConsumer = definitionUpdateEventConsumer;
            this.flowEventConsumer = flowEventConsumer;
            this.flowRunEventConsumer = flowRunEventConsumer;
            this.stageRunEventConsumer = stageRunEventConsumer;
        }

        @Bean
        @Override
        public List<KafkaConsumer<?>> getConsumers() {
            return List.of(
                    kafkaConsumerFactory.create(MicroServiceEventType.DEF_UPDATE,
                            StageDefinitionEvent.class,
                            (e) -> definitionUpdateEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName())),

                    kafkaConsumerFactory.create(MicroServiceEventType.STAGE_UPDATE,
                            StageUpdateEvent.class,
                            (e) -> stageUpdateEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName())),

                    kafkaConsumerFactory.create(MicroServiceEventType.FLOW_UPDATE,
                            FlowEvent.class,
                            (e) -> flowEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName())),

                    kafkaConsumerFactory.create(MicroServiceEventType.FLOW_RUN_UPDATE,
                            FlowRunEvent.class,
                            (e) -> flowRunEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName())),

                    kafkaConsumerFactory.create(MicroServiceEventType.STAGE_RUN_UPDATE,
                            StageRunEvent.class,
                            (e) -> stageRunEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName()))
            );
        }
    }
}
