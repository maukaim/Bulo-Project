package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.core.validators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ValidatorBeansConfig {

    @Bean
    public ParametersTSDValidator parametersTSDValidator(){
        return new ParametersTSDValidator();
    }

    @Bean
    public InputsTSDValidator inputsTSDValidator(){
        return new InputsTSDValidator();
    }

    @Bean
    public OutputsTSDValidator outputsTSDValidator(){
        return new OutputsTSDValidator();
    }

    @Bean
    public List<FunctionalStageDefinitionValidator> functionalStageValidators(StageDefinitionStore stageDefinitionStore,
                                                                              StageStore stageStore){
        return List.of(
                new ParametersFSDValidator(),
                new InputsFSDValidator(stageDefinitionStore,stageStore),
                new OutputsFSDValidator(stageStore, stageDefinitionStore),
                new FsStagesFSDValidator(stageStore)
        );
    }
}
