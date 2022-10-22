package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionServiceImpl;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServiceBeansConfig {
    @Bean
    public StageDefinitionService technicalStageDefinitionService(StageDefinitionStore definitionRepository,
                                                                  List<TechnicalStageDefinitionValidator> validators,
                                                                  List<FunctionalStageDefinitionValidator> functionalValidators){
        return new StageDefinitionServiceImpl(definitionRepository,validators, functionalValidators);
    }
}
