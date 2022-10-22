package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.stages.core.StageService;
import com.maukaim.bulo.stages.core.StageDefinitionService;
import com.maukaim.bulo.stages.core.impl.StageServiceImpl;
import com.maukaim.bulo.stages.core.impl.StageDefinitionServiceImpl;
import com.maukaim.bulo.stages.core.StageValidator;
import com.maukaim.bulo.stages.models.StageStore;
import com.maukaim.bulo.stages.models.StageDefinitionStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeansConfig {

    @Bean
    public StageDefinitionService technicalStageDefinitionService(StageDefinitionStore stageDefinitionStore) {
        return new StageDefinitionServiceImpl(stageDefinitionStore);
    }

    @Bean
    public StageService stageService(StageStore stageStore,
                                     StageDefinitionService stageDefinitionService,
                                     StageValidator validator) {
        return new StageServiceImpl(
                stageStore,
                stageDefinitionService,
                validator
        );
    }
}
