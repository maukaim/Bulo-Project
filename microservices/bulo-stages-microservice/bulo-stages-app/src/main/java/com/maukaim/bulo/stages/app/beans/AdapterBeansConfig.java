package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.stages.persistence.adapters.ParameterAdapter;
import com.maukaim.bulo.stages.persistence.adapters.ParameterDefinitionAdapter;
import com.maukaim.bulo.stages.persistence.adapters.StageAdapter;
import com.maukaim.bulo.stages.persistence.adapters.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.stages.persistence.adapters.impl.ParameterDefinitionAdapterImpl;
import com.maukaim.bulo.stages.persistence.adapters.impl.TechnicalStageDefinitionAdapterImpl;
import com.maukaim.bulo.stages.persistence.adapters.impl.ParameterAdapterImpl;
import com.maukaim.bulo.stages.persistence.adapters.impl.StageAdapterImpl;
import com.maukaim.bulo.stages.persistence.adapters.ParameterDataAdapter;
import com.maukaim.bulo.stages.persistence.adapters.impl.ParameterDataAdapterImpl;
import com.maukaim.bulo.stages.persistence.adapters.StageDtoAdapter;
import com.maukaim.bulo.stages.persistence.adapters.impl.StageDtoAdapterImpl;
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
    public ParameterDataAdapter parameterDataAdapter() {
        return new ParameterDataAdapterImpl();
    }

    @Bean
    public StageDtoAdapter stageDataAdapter(ParameterDataAdapter parameterDataAdapter) {
        return new StageDtoAdapterImpl(parameterDataAdapter);
    }

    @Bean
    public ParameterDefinitionAdapter parameterDefinitionAdapter() {
        return new ParameterDefinitionAdapterImpl();
    }

    @Bean
    public TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter(ParameterDefinitionAdapter parameterDefinitionAdapter) {
        return new TechnicalStageDefinitionAdapterImpl(parameterDefinitionAdapter);
    }
}
