package com.maukaim.bulo.standalone.app.beans.modules;

import com.maukaim.bulo.definitions.data.TechnicalStageDefinitionStore;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionService;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionServiceImpl;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.core.validators.InputsTSDValidator;
import com.maukaim.bulo.definitions.registry.core.validators.OutputsTSDValidator;
import com.maukaim.bulo.definitions.registry.core.validators.ParametersTSDValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DefinitionModuleBeansConfig {
    @Bean
    public TechnicalStageDefinitionService stageDefinitionService(TechnicalStageDefinitionStore definitionStore,
                                                                  List<TechnicalStageDefinitionValidator> validators
    ) {
        return new TechnicalStageDefinitionServiceImpl(definitionStore, validators);
    }

    @Bean
    public List<TechnicalStageDefinitionValidator> validators() {
        return List.of(
                new InputsTSDValidator(),
                new ParametersTSDValidator(),
                new OutputsTSDValidator()
        );
    }
}
