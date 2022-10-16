package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.data.lifecycle.adapters.*;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterBeansConfig {

    @Bean
    public TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter(ParameterDefinitionAdapter parameterDefinitionAdapter,
                                                                           StageInputDefinitionAdapter stageInputDefinitionAdapter,
                                                                           StageOutputDefinitionAdapter stageOutputDefinitionAdapter){
        return new TechnicalStageDefinitionAdapterImpl(parameterDefinitionAdapter, stageInputDefinitionAdapter, stageOutputDefinitionAdapter);
    }

    @Bean
    public ParameterDefinitionAdapter parameterDefinitionAdapter(){
        return new ParameterDefinitionAdapterImpl();
    }

    @Bean
    public StageInputDefinitionAdapter stageInputDefinitionAdapter(){
        return new StageInputDefinitionAdapterImpl();
    }

    @Bean
    public StageOutputDefinitionAdapter stageOutputDefinitionAdapter(){
        return new StageOutputDefinitionAdapterImpl();
    }

    @Bean
    public TechnicalStageDefinitionDtoAdapter technicalStageDefinitionDtoAdapter(ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter,
                                                                                 StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
                                                                                 StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter){
        return new TechnicalStageDefinitionDtoAdapterImpl(parameterDefinitionDtoAdapter,stageInputDefinitionDtoAdapter,stageOutputDefinitionDtoAdapter);
    }

    @Bean
    public ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter(){
        return new ParameterDefinitionDtoAdapterImpl();
    }

    @Bean
    public StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter(){
        return new StageInputDefinitionDtoAdapterImpl();
    }

    @Bean
    public StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter(){
        return new StageOutputDefinitionDtoAdapterImpl();
    }
}
