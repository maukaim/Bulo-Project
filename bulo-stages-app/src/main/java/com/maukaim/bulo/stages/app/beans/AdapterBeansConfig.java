package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.stages.core.ParameterAdapter;
import com.maukaim.bulo.stages.core.ParameterDefinitionAdapter;
import com.maukaim.bulo.stages.core.StageAdapter;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionAdapter;
import com.maukaim.bulo.stages.core.definitions.ParameterDefinitionAdapterImpl;
import com.maukaim.bulo.stages.core.definitions.TechnicalStageDefinitionAdapterImpl;
import com.maukaim.bulo.stages.core.stage.ParameterAdapterImpl;
import com.maukaim.bulo.stages.core.stage.StageAdapterImpl;
import com.maukaim.bulo.stages.persistence.adapters.ParameterDataAdapter;
import com.maukaim.bulo.stages.persistence.adapters.ParameterDataAdapterImpl;
import com.maukaim.bulo.stages.persistence.adapters.StageDataAdapter;
import com.maukaim.bulo.stages.persistence.adapters.StageDataAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterBeansConfig {

    @Bean
    public ParameterAdapter parameterAdapter(){
        return new ParameterAdapterImpl();
    }
    @Bean
    public StageAdapter stageAdapter(ParameterAdapter parameterAdapter){
        return new StageAdapterImpl(parameterAdapter);
    }

    @Bean
    public ParameterDataAdapter parameterDataAdapter(){
        return new ParameterDataAdapterImpl();
    }

    @Bean
    public StageDataAdapter stageDataAdapter(ParameterDataAdapter parameterDataAdapter){
        return new StageDataAdapterImpl(parameterDataAdapter);
    }

    @Bean
    public ParameterDefinitionAdapter parameterDefinitionAdapter(){
        return new ParameterDefinitionAdapterImpl();
    }

    @Bean
    public TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter(ParameterDefinitionAdapter parameterDefinitionAdapter){
        return new TechnicalStageDefinitionAdapterImpl(parameterDefinitionAdapter);
    }
}
