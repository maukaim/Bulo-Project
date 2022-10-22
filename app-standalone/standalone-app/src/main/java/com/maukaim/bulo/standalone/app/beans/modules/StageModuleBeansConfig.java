package com.maukaim.bulo.standalone.app.beans.modules;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.core.TechnicalStageValidator;
import com.maukaim.bulo.stages.core.impl.StageServiceImpl;
import com.maukaim.bulo.stages.core.impl.TechnicalStageDefinitionServiceImpl;
import com.maukaim.bulo.stages.core.validators.TechnicalStageValidatorImpl;
import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.StageDefinitionStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StageModuleBeansConfig {

    @Bean
    public StageService stageService(StageStore stageStore,
                                     TechnicalStageDefinitionService definitionService,
                                     TechnicalStageValidator stageValidator) {
        return new StageServiceImpl(stageStore, definitionService, stageValidator);
    }

    @Bean
    public TechnicalStageDefinitionService definitionService(StageDefinitionStore definitionStore) {
        return new TechnicalStageDefinitionServiceImpl(definitionStore);
    }

    @Bean
    public TechnicalStageValidator stageValidator() {
        return new TechnicalStageValidatorImpl();
    }
}
