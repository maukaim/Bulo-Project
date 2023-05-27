package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.data.lifecycle.flows.client.FlowAdapter;
import com.maukaim.bulo.data.lifecycle.flows.client.FlowDtoAdapter;
import com.maukaim.bulo.flows.app.io.CreateFlowInstructionConsumerImpl;
import com.maukaim.bulo.flows.app.io.FlowEventConsumerImpl;
import com.maukaim.bulo.flows.app.io.FlowEventPublisherImpl;
import com.maukaim.bulo.flows.app.io.GetAllFlowsInstructionConsumerImpl;
import com.maukaim.bulo.flows.app.io.RemoveFlowInstructionConsumerImpl;
import com.maukaim.bulo.flows.app.io.StageDefinitionConsumerImpl;
import com.maukaim.bulo.flows.app.io.StageUpdateEventConsumerImpl;
import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.StageStore;
import com.maukaim.bulo.flows.ms.data.lifecycle.FlowEventConsumer;
import com.maukaim.bulo.flows.ms.data.lifecycle.FlowEventPublisher;
import com.maukaim.bulo.flows.ms.data.lifecycle.FlowStoreImpl;
import com.maukaim.bulo.flows.ms.data.lifecycle.StageDefinitionConsumer;
import com.maukaim.bulo.flows.ms.data.lifecycle.StageUpdateEventConsumer;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageDefinitionAdapter;
import com.maukaim.bulo.io.definitions.system.StageDefinitionEvent;
import com.maukaim.bulo.io.flows.client.CreateFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.client.GetAllFlowsInstructionConsumer;
import com.maukaim.bulo.io.flows.client.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.system.FlowEvent;
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
    public FlowEventPublisher flowEventPublisher(SystemConnector<MicroServiceEventType> systemConnector) {
        return new FlowEventPublisherImpl(systemConnector);
    }

    @Bean
    public FlowEventConsumer flowEventConsumer(FlowStoreImpl flowStore,
                                               FlowAdapter flowAdapter) {
        return new FlowEventConsumerImpl(flowStore, flowAdapter);
    }

    @Bean
    CreateFlowInstructionConsumer createFlowInstructionConsumer(FlowService flowService,
                                                                FlowAdapter flowAdapter) {
        return new CreateFlowInstructionConsumerImpl(flowService, flowAdapter);
    }

    @Bean
    GetAllFlowsInstructionConsumer getAllFlowsInstructionConsumer(FlowService flowService,
                                                                  FlowDtoAdapter flowDtoAdapter){
        return new GetAllFlowsInstructionConsumerImpl(flowService, flowDtoAdapter);
    }

    @Bean
    public RemoveFlowInstructionConsumer removeFlowInstructionConsumer(FlowService flowService) {
        return new RemoveFlowInstructionConsumerImpl(flowService);
    }

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(StageAdapter stageAdapter,
                                                             StageStore stageStore) {
        return new StageUpdateEventConsumerImpl(stageStore, stageAdapter);
    }

    @Bean
    public StageDefinitionConsumer stageDefinitionConsumer(StageDefinitionStore definitionStore,
                                                           StageDefinitionAdapter definitionAdapter) {
        return new StageDefinitionConsumerImpl(definitionAdapter, definitionStore);
    }


    @Conditional(KafkaActivatedCondition.class)
    public class KafkaConsumerConfig extends KafkaConsumerProvider {
        private SystemContext systemContext;
        private StageDefinitionConsumer stageDefinitionEventConsumer;
        private StageUpdateEventConsumer stageUpdateEventConsumer;
        private FlowEventConsumer flowEventConsumer;

        @Autowired
        public KafkaConsumerConfig(
                SystemContext systemContext,
                StageUpdateEventConsumer stageUpdateEventConsumer,
                StageDefinitionConsumer stageDefinitionEventConsumer,
                FlowEventConsumer flowEventConsumer,
                KafkaConsumerFactory kafkaConsumerFactory) {
            super(kafkaConsumerFactory);
            this.systemContext = systemContext;
            this.stageUpdateEventConsumer = stageUpdateEventConsumer;
            this.stageDefinitionEventConsumer = stageDefinitionEventConsumer;
            this.flowEventConsumer = flowEventConsumer;
        }

        @Bean
        @Override
        public List<KafkaConsumer<?>> getConsumers() {
            return List.of(
                    kafkaConsumerFactory.create(MicroServiceEventType.FLOW_UPDATE,
                            FlowEvent.class,
                            (e) -> flowEventConsumer.consume(e),
                            OffsetResetStrategy.LATEST,
                            KafkaUtil.toGroupId(systemContext.getServiceName())),

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