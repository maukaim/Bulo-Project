package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.core.*;
import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeansConfig {

    @Bean
    public FlowService flowServiceImpl(FlowStore flowStore){
        return new FlowServiceImpl(flowStore);
    }

    @Bean
    public FlowRunService flowRunServiceImpl(FlowService flowService,
                                             FlowRunStore flowRunStore,
                                             StageRunService stageRunService){
        return new FlowRunServiceImpl(flowService, flowRunStore, stageRunService);
    }

    @Bean
    public StageRunService stageRunService(StageRunStore stageRunStore,
                                           StageRunConnector stageRunConnector,
                                           FunctionalStageService functionalStageService,
                                           FunctionalStageDefinitionService functionalStageDefinitionService){
        return new StageRunServiceImpl(stageRunConnector, stageRunStore, 4,
                functionalStageService, functionalStageDefinitionService);
    }

    @Bean
    public FunctionalStageService functionalStageService(FunctionalStageStore functionalStageStore){
        return new FunctionalStageServiceImpl(functionalStageStore);
    }

    @Bean
    public FunctionalStageDefinitionService functionalStageDefinitionService(FunctionalStageDefinitionStore functionalStageDefinitionStore){
        return new FunctionalStageDefinitionServiceImpl(functionalStageDefinitionStore);
    }
}
