package com.maukaim.bulo.executor.app.beans;

import com.maukaim.bulo.executor.data.management.adapters.*;
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
    public TechnicalStageDefinitionDtoAdapter technicalStageDefinitionDtoAdapter(
            ParameterDefinitionDtoAdapter parameterDefinitionDtoAdapter,
            StageInputDefinitionDtoAdapter stageInputDefinitionDtoAdapter,
            StageOutputDefinitionDtoAdapter stageOutputDefinitionDtoAdapter) {
        return new TechnicalStageDefinitionDtoDtoAdapterImpl(
                stageInputDefinitionDtoAdapter,
                stageOutputDefinitionDtoAdapter,
                parameterDefinitionDtoAdapter);
    }
}
