package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.stages.core.*;
import com.maukaim.bulo.stages.core.definitions.TechnicalStageDefinitionServiceImpl;
import com.maukaim.bulo.stages.core.stage.StageServiceImpl;
import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.TechnicalStageDefinitionStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeansConfig {

    @Bean
    public TechnicalStageDefinitionService technicalStageDefinitionService(TechnicalStageDefinitionStore technicalStageDefinitionStore,
                                                                           TechnicalStageDefinitionAdapter technicalStageDefinitionAdapter) {
        return new TechnicalStageDefinitionServiceImpl(technicalStageDefinitionStore, technicalStageDefinitionAdapter);
    }

    @Bean
    public StageService stageService(StageStore stageStore,
                                     TechnicalStageDefinitionService technicalStageDefinitionService,
                                     TechnicalStageValidator validator,
                                     StageAdapter stageAdapter) {
        return new StageServiceImpl(
                stageStore,
                technicalStageDefinitionService,
                validator,
                stageAdapter
        );
    }
}
