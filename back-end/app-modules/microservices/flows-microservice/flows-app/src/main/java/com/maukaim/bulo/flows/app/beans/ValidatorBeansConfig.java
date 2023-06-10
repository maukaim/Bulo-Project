package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.common.utils.IoTypeComparator;
import com.maukaim.bulo.common.utils.ParameterTypeComparator;
import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.ContextStageId;
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
                                       StageInputValidator stageInputValidator,
                                       AcyclicValidator<ContextStageId> acyclicValidator
    ) {
        return new FlowValidatorImpl(stageService, definitionService, stageParameterValidator, flowStageIoValidator, stageInputValidator, acyclicValidator);
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
                                                     DefinitionService definitionService,
                                                     IoTypeComparator ioTypeComparator) {
        return new FlowStageIoValidatorImpl(definitionService, stageService, ioTypeComparator);
    }

    @Bean
    public IoTypeComparator ioTypeComparator(){
        return new IoTypeComparator();
    }

    @Bean
    public AcyclicValidator<ContextStageId> acyclicValidator(){
        return new AcyclicValidator<>();
    }

    @Bean
    public StageInputValidator stageInputValidator() {
        return new StageInputValidatorImpl();
    }
}
