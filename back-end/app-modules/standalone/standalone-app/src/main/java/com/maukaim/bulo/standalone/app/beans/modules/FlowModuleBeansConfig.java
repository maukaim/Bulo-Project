package com.maukaim.bulo.standalone.app.beans.modules;

import com.maukaim.bulo.common.utils.IoTypeComparator;
import com.maukaim.bulo.common.utils.ParameterTypeComparator;
import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.flows.core.DefinitionService;
import com.maukaim.bulo.flows.core.FlowService;
import com.maukaim.bulo.flows.core.FlowStageIoValidator;
import com.maukaim.bulo.flows.core.FlowValidator;
import com.maukaim.bulo.flows.core.StageInputValidator;
import com.maukaim.bulo.flows.core.StageInputValidatorImpl;
import com.maukaim.bulo.flows.core.StageParameterValidator;
import com.maukaim.bulo.flows.core.StageParameterValidatorImpl;
import com.maukaim.bulo.flows.core.StageService;
import com.maukaim.bulo.flows.core.impl.DefinitionServiceImpl;
import com.maukaim.bulo.flows.core.impl.FlowServiceImpl;
import com.maukaim.bulo.flows.core.impl.FlowStageIoValidatorImpl;
import com.maukaim.bulo.flows.core.impl.FlowValidatorImpl;
import com.maukaim.bulo.flows.core.impl.StageServiceImpl;
import com.maukaim.bulo.flows.data.FlowStore;
import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.StageStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowModuleBeansConfig {

    @Bean
    public FlowService flowService(FlowStore flowStore,
                                   FlowValidator flowValidator) {
        return new FlowServiceImpl(flowStore, flowValidator);
    }

    @Bean
    public FlowValidator flowValidator(StageService stageService,
                                       DefinitionService definitionService,
                                       StageParameterValidator stageParameterValidator,
                                       FlowStageIoValidator flowStageIoValidator,
                                       StageInputValidator stageInputValidator,
                                       AcyclicValidator<ContextStageId> acyclicValidator) {
        return new FlowValidatorImpl(stageService, definitionService, stageParameterValidator, flowStageIoValidator, stageInputValidator, acyclicValidator);
    }

    @Bean
    public StageParameterValidator stageParameterValidator(ParameterTypeComparator parameterTypeComparator) {
        return new StageParameterValidatorImpl(parameterTypeComparator);
    }

    @Bean
    public FlowStageIoValidator flowStageIoValidator(DefinitionService definitionService,
                                                     StageService stageService, IoTypeComparator ioTypeComparator) {
        return new FlowStageIoValidatorImpl(definitionService, stageService, ioTypeComparator);
    }

    @Bean
    public StageInputValidator stageInputValidator() {
        return new StageInputValidatorImpl();
    }

    @Bean
    public DefinitionService definitionServicFlowModule(StageDefinitionStore stageDefinitionStore) {
        return new DefinitionServiceImpl(stageDefinitionStore);
    }

    @Bean
    public StageService stageServiceFlowModule(StageStore stageStore) {
        return new StageServiceImpl(stageStore);
    }
}
