package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinitionStore;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionServiceImpl;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServiceBeansConfig {
    @Bean
    public TechnicalStageDefinitionService technicalStageDefinitionService(TechnicalStageDefinitionStore definitionRepository,
                                                                           List<TechnicalStageDefinitionValidator> validators){
        return new TechnicalStageDefinitionServiceImpl(definitionRepository,validators);
    }
}
