package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.common.utils.ParameterTypeComparator;
import com.maukaim.bulo.flows.core.DefinitionService;
import com.maukaim.bulo.flows.core.FlowStageIoValidator;
import com.maukaim.bulo.flows.core.FlowValidator;
import com.maukaim.bulo.flows.core.StageInputValidator;
import com.maukaim.bulo.flows.core.StageInputValidatorImpl;
import com.maukaim.bulo.flows.core.StageParameterValidator;
import com.maukaim.bulo.flows.core.StageParameterValidatorImpl;
import com.maukaim.bulo.flows.core.StageService;
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
    ) {
        return new FlowValidatorImpl(stageService, definitionService, stageParameterValidator, flowStageIoValidator, stageInputValidator);
    }

    @Bean
    public StageParameterValidator stageParameterValidator(ParameterTypeComparator parameterTypeComparator) {
        return new StageParameterValidatorImpl(parameterTypeComparator);
    }

    @Bean
    public ParameterTypeComparator parameterTypeComparator() {
        return new ParameterTypeComparator();
    }

    @Bean
    public FlowStageIoValidator flowStageIoValidator(StageService stageService,
                                                     DefinitionService definitionService) {
        return new FlowStageIoValidatorImpl(definitionService, stageService);
    }

    @Bean
    public StageInputValidator stageInputValidator() {
        return new StageInputValidatorImpl();
    }
}
