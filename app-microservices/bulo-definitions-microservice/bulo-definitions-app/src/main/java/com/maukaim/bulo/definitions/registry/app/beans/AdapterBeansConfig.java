package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.data.lifecycle.*;
import com.maukaim.bulo.definitions.data.lifecycle.functional.*;
import com.maukaim.bulo.definitions.data.lifecycle.functional.impl.*;
import com.maukaim.bulo.definitions.data.lifecycle.impl.*;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.stages.ParameterAdapter;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.stages.StageAdapter;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.stages.impl.ParameterAdapterImpl;
import com.maukaim.bulo.definitions.ms.data.lifecycle.adapters.stages.impl.StageAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterBeansConfig {

    @Bean
    public StageDefinitionAdapter technicalStageDefinitionAdapter(ParameterDefinitionAdapter parameterDefinitionAdapter,
                                                                  StageInputDefinitionAdapter stageInputDefinitionAdapter,
                                                                  StageOutputDefinitionAdapter stageOutputDefinitionAdapter,
                                                                  FunctionalSubStageAdapter functionalSubStageAdapter){
        return new StageDefinitionAdapterImpl(parameterDefinitionAdapter, stageInputDefinitionAdapter, stageOutputDefinitionAdapter, functionalSubStageAdapter);
    }

    @Bean
    public FunctionalSubStageAdapter functionalSubStageAdapter(IoDependencyAdapter ioDependencyAdapter){
        return new FunctionalSubStageAdapterImpl(ioDependencyAdapter);
    }

    @Bean
    public IoDependencyAdapter ioDependencyAdapter(InputProviderAdapter inputProviderAdapter){
        return new IoDependencyAdapterImpl(inputProviderAdapter);
    }

    @Bean
    public InputProviderAdapter inputProviderAdapter(){
        return new InputProviderAdapterImpl();
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
    public StageDefinitionDtoAdapter technicalStageDefinitionDtoAdapter(ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter,
                                                                        StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
                                                                        StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter,
                                                                        FunctionalSubStageDtoAdapter functionalSubStageDtoAdapter){
        return new StageDefinitionDtoAdapterImpl(parameterDefinitionDtoAdapter,stageInputDefinitionDtoAdapter,stageOutputDefinitionDtoAdapter, functionalSubStageDtoAdapter);
    }

    @Bean
    public FunctionalSubStageDtoAdapter functionalSubStageDtoAdapter(IoDependencyDtoAdapter ioDependencyDtoAdapter){
        return new FunctionalSubStageDtoAdapterImpl(ioDependencyDtoAdapter);
    }

    @Bean
    public IoDependencyDtoAdapter ioDependencyDtoAdapter(InputProviderDtoAdapter inputProviderDtoAdapter){
        return new IoDependencyDtoAdapterImpl(inputProviderDtoAdapter);
    }

    @Bean
    public InputProviderDtoAdapter inputProviderDtoAdapter(){
        return new InputProviderDtoAdapterImpl();
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

    @Bean
    public StageAdapter stageAdapter(ParameterAdapter parameterAdapter){
        return new StageAdapterImpl(parameterAdapter);
    }

    @Bean
    public ParameterAdapter parameterAdapter(){
        return new ParameterAdapterImpl();
    }
}
