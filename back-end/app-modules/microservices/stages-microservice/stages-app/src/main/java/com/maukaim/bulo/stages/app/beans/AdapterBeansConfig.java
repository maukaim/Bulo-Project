package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.data.lifecycle.stages.client.ParameterAdapter;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.data.lifecycle.stages.client.StageAdapter;
import com.maukaim.bulo.data.lifecycle.data.types.client.ParameterTypeAdapter;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.StageDefinitionAdapter;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.impl.ParameterDefinitionAdapterImpl;
import com.maukaim.bulo.data.lifecycle.data.types.client.impl.ParameterTypeAdapterImpl;
import com.maukaim.bulo.stages.ms.data.lifecycle.adapters.impl.StageDefinitionAdapterImpl;
import com.maukaim.bulo.data.lifecycle.stages.client.impl.ParameterAdapterImpl;
import com.maukaim.bulo.data.lifecycle.stages.client.impl.StageAdapterImpl;
import com.maukaim.bulo.data.lifecycle.stages.client.ParameterDtoAdapter;
import com.maukaim.bulo.data.lifecycle.stages.client.impl.ParameterDtoAdapterImpl;
import com.maukaim.bulo.data.lifecycle.stages.client.StageDtoAdapter;
import com.maukaim.bulo.data.lifecycle.stages.client.impl.StageDtoAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterBeansConfig {

    @Bean
    public ParameterAdapter parameterAdapter() {
        return new ParameterAdapterImpl();
    }

    @Bean
    public StageAdapter stageAdapter(ParameterAdapter parameterAdapter) {
        return new StageAdapterImpl(parameterAdapter);
    }

    @Bean
    public ParameterDtoAdapter parameterDataAdapter() {
        return new ParameterDtoAdapterImpl();
    }

    @Bean
    public StageDtoAdapter stageDataAdapter(ParameterDtoAdapter parameterDtoAdapter) {
        return new StageDtoAdapterImpl(parameterDtoAdapter);
    }

    @Bean
    public ParameterDefinitionAdapter parameterDefinitionAdapter(ParameterTypeAdapter parameterTypeAdapter) {
        return new ParameterDefinitionAdapterImpl(parameterTypeAdapter);
    }

    @Bean
    public ParameterTypeAdapter parameterTypeAdapter(){
        return new ParameterTypeAdapterImpl();
    }

    @Bean
    public StageDefinitionAdapter technicalStageDefinitionAdapter(ParameterDefinitionAdapter parameterDefinitionAdapter) {
        return new StageDefinitionAdapterImpl(parameterDefinitionAdapter);
    }
}
