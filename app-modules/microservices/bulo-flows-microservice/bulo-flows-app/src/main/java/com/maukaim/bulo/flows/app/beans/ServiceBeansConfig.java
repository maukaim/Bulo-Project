package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.flows.core.DefinitionService;
import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.core.FlowValidator;
import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.core.impl.DefinitionServiceImpl;
import com.maukaim.bulo.flows.core.impl.FlowServiceImpl;
import com.maukaim.bulo.flows.core.impl.StageServiceImpl;
import com.maukaim.bulo.flows.data.FlowStore;
import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.StageStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeansConfig {

    @Bean
    public StageService stageService(StageStore stageStore){
        return new StageServiceImpl(stageStore);
    }

    @Bean
    DefinitionService definitionService(StageDefinitionStore stageDefinitionStore){
        return new DefinitionServiceImpl(stageDefinitionStore);
    }

    @Bean
    public FlowService flowService(FlowStore flowStore,
                                   FlowValidator flowValidator){
        return new FlowServiceImpl(flowStore, flowValidator);
    }
}
