package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.flows.app.io.*;
import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.StageStore;
import com.maukaim.bulo.flows.data.lifecycle.FlowStoreImpl;
import com.maukaim.bulo.flows.data.lifecycle.adapters.FlowAdapter;
import com.maukaim.bulo.flows.data.lifecycle.adapters.StageAdapter;
import com.maukaim.bulo.flows.data.lifecycle.adapters.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.flows.io.*;
import com.maukaim.bulo.ms.connectivity.SystemConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IoBeansConfig {

    @Bean
    public FlowEventPublisher flowEventPublisher(SystemConnector systemConnector) {
        return new FlowEventPublisherImpl(systemConnector);
    }

    @Bean
    public SystemConnector systemConnector(){
        return new SystemConnector(new RestTemplate());
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
    public TechnicalStageDefinitionConsumer technicalStageDefinitionConsumer(StageDefinitionStore definitionStore,
                                                                             TechnicalStageDefinitionAdapter definitionAdapter){
        return new TechnicalStageDefinitionConsumerImpl(definitionAdapter, definitionStore);
    }
}