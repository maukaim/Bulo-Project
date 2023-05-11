package com.maukaim.bulo.standalone.app.beans.modules;

import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionServiceImpl;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.core.validators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DefinitionModuleBeansConfig {
    @Bean
    public StageDefinitionService stageDefinitionService(StageDefinitionStore definitionStore,
                                                         List<TechnicalStageDefinitionValidator> validators,
                                                         List<FunctionalStageDefinitionValidator> functionalValidators
    ) {
        return new StageDefinitionServiceImpl(definitionStore, validators, functionalValidators);
    }

    @Bean
    public List<TechnicalStageDefinitionValidator> technicalValidators() {
        return List.of(
                new InputsTSDValidator(),
                new ParameterDefinitionTSDValidator(),
                new OutputsTSDValidator()
        );
    }

    @Bean
    public List<FunctionalStageDefinitionValidator> functionalValidators(StageDefinitionStore stageDefinitionStore,
                                                                         StageStore stageStore) {
        return List.of(
                new InputsFSDValidator(stageDefinitionStore, stageStore),
                new ParameterDefinitionsFSDValidator(),
                new OutputsFSDValidator(stageStore, stageDefinitionStore),
                new FsStagesFSDValidator(stageStore)
        );
    }
}
