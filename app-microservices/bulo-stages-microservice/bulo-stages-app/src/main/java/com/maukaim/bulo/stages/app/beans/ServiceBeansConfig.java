package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.stages.core.impl.StageServiceImpl;
import com.maukaim.bulo.stages.core.impl.TechnicalStageDefinitionServiceImpl;
import com.maukaim.bulo.stages.core.TechnicalStageValidator;
import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.StageDefinitionStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeansConfig {

    @Bean
    public TechnicalStageDefinitionService technicalStageDefinitionService(StageDefinitionStore stageDefinitionStore) {
        return new TechnicalStageDefinitionServiceImpl(stageDefinitionStore);
    }

    @Bean
    public StageService stageService(StageStore stageStore,
                                     TechnicalStageDefinitionService technicalStageDefinitionService,
                                     TechnicalStageValidator validator) {
        return new StageServiceImpl(
                stageStore,
                technicalStageDefinitionService,
                validator
        );
    }
}
