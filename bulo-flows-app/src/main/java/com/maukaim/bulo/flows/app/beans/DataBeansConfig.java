package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.flows.data.FlowStore;
import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.StageStore;
import com.maukaim.bulo.flows.data.lifecycle.FlowStoreImpl;
import com.maukaim.bulo.flows.data.lifecycle.StageDefinitionStoreImpl;
import com.maukaim.bulo.flows.data.lifecycle.StageStoreImpl;
import com.maukaim.bulo.flows.data.lifecycle.adapters.FlowDtoAdapter;
import com.maukaim.bulo.flows.io.FlowEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DataBeansConfig {

    @Bean
    public StageStore stageStore(){
        return new StageStoreImpl(Map.of());
    }

    @Bean
    public StageDefinitionStore stageDefinitionStore(){
        return new StageDefinitionStoreImpl(Map.of());
    }

    @Bean
    public FlowStoreImpl flowStoreImpl(FlowEventPublisher flowEventPublisher,
                               FlowDtoAdapter flowDtoAdapter){
        return new FlowStoreImpl(Map.of(), flowEventPublisher, flowDtoAdapter);
    }
}
