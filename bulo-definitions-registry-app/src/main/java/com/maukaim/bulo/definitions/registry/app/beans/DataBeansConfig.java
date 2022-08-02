package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionRepository;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBeansConfig {
    @Bean
    public TechnicalStageDefinitionRepository definitionRepository() {
        return new TechnicalStageDefinitionRepositoryImpl();
    }
}
