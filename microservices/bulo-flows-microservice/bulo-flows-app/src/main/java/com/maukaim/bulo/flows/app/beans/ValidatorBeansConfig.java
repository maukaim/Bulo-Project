package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.flows.core.*;
import com.maukaim.bulo.flows.core.impl.FlowStageIoValidatorImpl;
import com.maukaim.bulo.flows.core.impl.FlowValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorBeansConfig {
    @Bean
    public FlowValidator flowValidator(StageService stageService,
                                       DefinitionService definitionService,
                                       StageParameterValidator stageParameterValidator,
                                       FlowStageIoValidator flowStageIoValidator,
                                       StageInputValidator stageInputValidator
                                       ){
        return new FlowValidatorImpl(stageService,definitionService, stageParameterValidator, flowStageIoValidator, stageInputValidator);
    }

    @Bean
    public StageParameterValidator stageParameterValidator(){
        return new StageParameterValidatorImpl();
    }

    @Bean
    public FlowStageIoValidator flowStageIoValidator(StageService stageService,
                                                     DefinitionService definitionService){
        return new FlowStageIoValidatorImpl(definitionService, stageService);
    }

    @Bean
    public StageInputValidator stageInputValidator(){
        return new StageInputValidatorImpl();
    }
}
