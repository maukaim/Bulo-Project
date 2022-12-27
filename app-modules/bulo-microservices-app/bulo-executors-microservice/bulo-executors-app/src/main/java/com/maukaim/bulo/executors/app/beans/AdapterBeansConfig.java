package com.maukaim.bulo.executors.app.beans;

import com.maukaim.bulo.common.data.lifecycle.IoTypeDtoAdapter;
import com.maukaim.bulo.common.data.lifecycle.impl.IoTypeDtoAdapterImpl;
import com.maukaim.bulo.common.data.lifecycle.ParameterTypeDtoAdapter;
import com.maukaim.bulo.common.data.lifecycle.impl.ParameterTypeDtoAdapterImpl;
import com.maukaim.bulo.executors.data.lifecycle.adapters.*;
import com.maukaim.bulo.executors.data.lifecycle.adapters.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterBeansConfig {

    @Bean
    public ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter(ParameterTypeDtoAdapter parameterTypeDtoAdapter) {
        return new ParameterDefinitionDtoAdapterImpl(parameterTypeDtoAdapter);
    }

    @Bean
    public ParameterTypeDtoAdapter parameterTypeDtoAdapter(){
        return new ParameterTypeDtoAdapterImpl();
    }

    @Bean
    public StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter(IoTypeDtoAdapter ioTypeDtoAdapter) {
        return new StageInputDefinitionDtoAdapterImpl(ioTypeDtoAdapter);
    }

    @Bean
    public StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter(IoTypeDtoAdapter ioTypeDtoAdapter) {
        return new StageOutputDefinitionDtoAdapterImpl(ioTypeDtoAdapter);
    }

    @Bean
    public IoTypeDtoAdapter ioTypeDtoAdapter(){
        return new IoTypeDtoAdapterImpl();
    }

    @Bean
    public StageDefinitionDtoAdapter stageDefinitionDtoAdapter(ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter,
                                                                        StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
                                                                        StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter) {
        return new StageDefinitionDtoDtoAdapterImpl(
                stageInputDefinitionDtoAdapter,
                stageOutputDefinitionDtoAdapter,
                parameterDefinitionDtoAdapter);
    }

    @Bean
    public StageRunDependencyAdapter stageRunDependencyAdapter(StageRunAncestorAdapter stageRunAncestorAdapter) {
        return new StageRunDependencyAdapterImpl(stageRunAncestorAdapter);
    }

    @Bean
    public StageRunAncestorAdapter stageRunAncestorAdapter() {
        return new StageRunAncestorAdapterImpl();
    }

    @Bean
    public StageRunResultDtoAdapter stageRunResultDtoAdapter() {
        return new StageRunResultDtoAdapterImpl();
    }

    @Bean
    public StageRunResultAdapter stageRunResultAdapter() {
        return new StageRunResultAdapterImpl();
    }

    @Bean
    public StageAdapter stageAdapter(ParameterAdapter parameterAdapter) {
        return new StageAdapterImpl(parameterAdapter);
    }

    @Bean
    public ParameterAdapter parameterAdapter() {
        return new ParameterAdapterImpl();
    }
}
