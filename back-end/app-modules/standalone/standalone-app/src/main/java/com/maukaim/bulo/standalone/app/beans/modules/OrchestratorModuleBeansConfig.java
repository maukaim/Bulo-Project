package com.maukaim.bulo.standalone.app.beans.modules;

import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.*;
import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.*;
import com.maukaim.bulo.standalone.app.connectivity.StageRunConnectorImpl;
import com.maukaim.bulo.standalone.data.lifecycle.StageRunResultListener;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.StageRunDependencyAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class OrchestratorModuleBeansConfig {
    @Bean
    public FlowService flowServiceOrchestratorModule(FlowStore flowStore) {
        return new FlowServiceImpl(flowStore);
    }

    @Bean
    public StageRunService stageRunService(StageRunStore stageRunStore,
                                           StageRunConnector stageRunConnector,
                                           FunctionalStageService functionalStageService,
                                           FunctionalStageDefinitionService functionalStageDefinitionService) {
        return new StageRunServiceImpl(stageRunConnector, stageRunStore, 4, functionalStageService, functionalStageDefinitionService);
    }

    @Bean
    public FlowRunService flowRunService(FlowService flowService,
                                         StageRunService stageRunService,
                                         FlowRunStore flowRunStore) {
        return new FlowRunServiceImpl(flowService, flowRunStore, stageRunService);
    }

    @Bean
    public StageRunConnectorImpl stageRunConnector(StageRunEventProcessor stageRunEventProcessor,
                                               StageRunDependencyAdapter stageRunDependencyAdapter) {
        return new StageRunConnectorImpl(stageRunDependencyAdapter, stageRunEventProcessor);
    }

    @Bean
    public FunctionalStageService functionalStageService(FunctionalStageStore functionalStageStore){
        return new FunctionalStageServiceImpl(functionalStageStore);
    }

    @Bean
    public FunctionalStageDefinitionService functionalStageDefinitionService(FunctionalStageDefinitionStore definitionStore){
        return new FunctionalStageDefinitionServiceImpl(definitionStore);
    }

    @Configuration
    public static class StageRunEventProcessorsConfig {
        @Bean
        public AcknowledgeStageRunEventProcessor acknowledgeStageEventProcessor(StageRunService stageRunService,
                                                                                FlowRunService flowRunService) {
            return new AcknowledgeStageRunEventProcessor(flowRunService, stageRunService);
        }

        @Bean
        public StartRunStageRunEventProcessor startRunStageEventProcessor(StageRunService stageRunService,
                                                                          FlowRunService flowRunService) {
            return new StartRunStageRunEventProcessor(flowRunService, stageRunService);
        }

        @Bean
        public RunFailedStageRunEventProcessor runFailedStageEventProcessor(StageRunService stageRunService,
                                                                            FlowRunService flowRunService) {
            return new RunFailedStageRunEventProcessor(flowRunService, stageRunService);
        }

        @Bean
        public RunCancelledStageRunEventProcessor runCancelledStageEventProcessor(FlowRunService flowRunService,
                                                                                  StageRunService stageRunService) {
            return new RunCancelledStageRunEventProcessor(flowRunService, stageRunService);
        }

        @Bean
        public RunSuccessfulStageRunEventProcessor runSuccessfulStageEventProcessor(StageRunService stageRunService,
                                                                                    FlowRunService flowRunService) {
            return new RunSuccessfulStageRunEventProcessor(flowRunService, stageRunService);
        }
    }

    @Configuration
    public static class FunctionalStageRunCircularDependencyResolver {

        private final StageRunResultListener listener;
        private final StageRunConnectorImpl connector;

        @Autowired
        public FunctionalStageRunCircularDependencyResolver(StageRunResultListener listener, StageRunConnectorImpl connector) {
            this.listener = listener;
            this.connector = connector;
        }

        @PostConstruct
        public void injectStageRunEventConsumerInStageRunConnector() {
            connector.setStageRunResultListener(listener);
        }
    }

}
