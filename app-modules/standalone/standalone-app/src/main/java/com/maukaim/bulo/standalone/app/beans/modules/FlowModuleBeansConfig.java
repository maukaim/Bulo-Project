package com.maukaim.bulo.standalone.app.beans.modules;

import com.maukaim.bulo.flows.core.*;
import com.maukaim.bulo.flows.core.impl.*;
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
                                       StageInputValidator stageInputValidator) {
        return new FlowValidatorImpl(stageService, definitionService, stageParameterValidator, flowStageIoValidator, stageInputValidator);
    }

    @Bean
    public StageParameterValidator stageParameterValidator() {
        return new StageParameterValidatorImpl();
    }

    @Bean
    public FlowStageIoValidator flowStageIoValidator(DefinitionService definitionService,
                                                     StageService stageService) {
        return new FlowStageIoValidatorImpl(definitionService, stageService);
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
