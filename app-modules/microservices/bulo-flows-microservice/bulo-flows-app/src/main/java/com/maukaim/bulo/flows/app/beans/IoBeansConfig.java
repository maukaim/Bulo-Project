package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.flows.app.io.*;
import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.StageStore;
import com.maukaim.bulo.flows.ms.data.lifecycle.FlowStoreImpl;
import com.maukaim.bulo.flows.data.lifecycle.FlowAdapter;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.flows.ms.data.lifecycle.adapters.StageDefinitionAdapter;
import com.maukaim.bulo.flows.io.*;
import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoBeansConfig {

    @Bean
    public FlowEventPublisher flowEventPublisher(SystemConnector systemConnector) {
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
    public RemoveFlowInstructionConsumer removeFlowInstructionConsumer(FlowService flowService){
        return new RemoveFlowInstructionConsumerImpl(flowService);
    }

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(StageAdapter stageAdapter,
                                                             StageStore stageStore){
        return new StageUpdateEventConsumerImpl(stageStore, stageAdapter);
    }

    @Bean
    public StageDefinitionConsumer stageDefinitionConsumer(StageDefinitionStore definitionStore,
                                                                    StageDefinitionAdapter definitionAdapter){
        return new StageDefinitionConsumerImpl(definitionAdapter, definitionStore);
    }
}