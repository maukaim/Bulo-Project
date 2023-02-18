package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.flows.app.io.CreateFlowInstructionConsumerImpl;
import com.maukaim.bulo.flows.app.io.FlowEventConsumerImpl;
import com.maukaim.bulo.flows.app.io.FlowEventPublisherImpl;
import com.maukaim.bulo.flows.app.io.RemoveFlowInstructionConsumerImpl;
import com.maukaim.bulo.flows.app.io.StageDefinitionConsumerImpl;
import com.maukaim.bulo.flows.app.io.StageUpdateEventConsumerImpl;
import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.StageStore;
import com.maukaim.bulo.flows.data.lifecycle.FlowAdapter;
import com.maukaim.bulo.flows.ms.data.lifecycle.FlowStoreImpl;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageDefinitionAdapter;
import com.maukaim.bulo.io.flows.system.CreateFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.system.FlowEventConsumer;
import com.maukaim.bulo.io.flows.system.FlowEventPublisher;
import com.maukaim.bulo.io.flows.system.RemoveFlowInstructionConsumer;
import com.maukaim.bulo.io.flows.system.StageDefinitionConsumer;
import com.maukaim.bulo.io.flows.system.StageUpdateEventConsumer;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoBeansConfig {

    @Bean
    public FlowEventPublisher flowEventPublisher(SystemConnector<ServiceEventType> systemConnector) {
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
}