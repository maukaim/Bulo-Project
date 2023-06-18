package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.FlowService;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.factories.FlowRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.impl.FlowRunServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.impl.FlowServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.impl.FunctionalStageDefinitionServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.impl.FunctionalStageServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.impl.StageRunServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.utils.FlowUtils;
import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageDefinitionStore;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeansConfig {
    @Bean
    public FlowService flowServiceImpl(FlowStore flowStore) {
        return new FlowServiceImpl(flowStore);
    }

    @Bean
    public FlowRunService flowRunServiceImpl(FlowService flowService,
                                             FlowRunStore flowRunStore,
                                             StageRunService stageRunService,
                                             FlowUtils flowUtils,
                                             FlowRunFactory flowRunFactory) {
        return new FlowRunServiceImpl(flowService, flowRunStore, stageRunService, flowUtils, flowRunFactory);
    }

    @Bean
    public StageRunService stageRunService(StageRunStore stageRunStore,
                                           StageRunConnector stageRunConnector,
                                           FunctionalStageService functionalStageService,
                                           FunctionalStageDefinitionService functionalStageDefinitionService) {
        return new StageRunServiceImpl(stageRunConnector, stageRunStore, 4,
                functionalStageService, functionalStageDefinitionService);
    }

    @Bean
    public FunctionalStageService functionalStageService(FunctionalStageStore functionalStageStore) {
        return new FunctionalStageServiceImpl(functionalStageStore);
    }

    @Bean
    public FunctionalStageDefinitionService functionalStageDefinitionService(FunctionalStageDefinitionStore functionalStageDefinitionStore) {
        return new FunctionalStageDefinitionServiceImpl(functionalStageDefinitionStore);
    }
}
