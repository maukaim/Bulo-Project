package com.maukaim.bulo.standalone.app.beans.modules;

import com.maukaim.bulo.common.utils.ParameterTypeComparator;
import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.StageValidator;
import com.maukaim.bulo.stages.core.impl.StageDefinitionServiceImpl;
import com.maukaim.bulo.stages.core.impl.StageServiceImpl;
import com.maukaim.bulo.stages.core.validators.StageValidatorImpl;
import com.maukaim.bulo.stages.models.StageDefinitionStore;
import com.maukaim.bulo.stages.models.StageStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StageModuleBeansConfig {

    @Bean
    public StageService stageService(StageStore stageStore,
                                     StageDefinitionService definitionService,
                                     StageValidator stageValidator) {
        return new StageServiceImpl(stageStore, definitionService, stageValidator);
    }

    @Bean
    public StageDefinitionService definitionService(StageDefinitionStore definitionStore) {
        return new StageDefinitionServiceImpl(definitionStore);
    }

    @Bean
    public StageValidator stageValidator(ParameterTypeComparator parameterTypeComparator) {
        return new StageValidatorImpl(parameterTypeComparator);
    }
}
