package com.maukaim.bulo.executors.app.beans;

import com.maukaim.bulo.executors.data.lifecycle.adapters.*;
import com.maukaim.bulo.executors.data.lifecycle.adapters.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterBeansConfig {

    @Bean
    public ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter() {
        return new ParameterDefinitionDtoAdapterImpl();
    }

    @Bean
    public StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter() {
        return new StageInputDefinitionDtoAdapterImpl();
    }

    @Bean
    public StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter() {
        return new StageOutputDefinitionDtoAdapterImpl();
    }

    @Bean
    public StageDefinitionDtoAdapter technicalStageDefinitionDtoAdapter(ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter,
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
