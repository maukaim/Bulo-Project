package com.maukaim.bulo.standalone.app.beans.modules;


import com.maukaim.bulo.executors.core.StageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.*;
import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.standalone.app.connectivity.StageRunConnectorImpl;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.StageRunDependencyAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrchestratorModuleBeansConfig {
    @Bean
    public FlowService flowServiceOrchestratorModule(FlowStore flowStore) {
        return new FlowServiceImpl(flowStore);
    }

    @Bean
    public StageRunService stageRunService(StageRunStore stageRunStore,
                                           StageRunConnector stageRunConnector) {
        return new StageRunServiceImpl(stageRunConnector, stageRunStore);
    }

    @Bean
    public FlowRunService flowRunService(FlowService flowService,
                                         StageRunService stageRunService,
                                         FlowRunStore flowRunStore) {
        return new FlowRunServiceImpl(flowService, flowRunStore, stageRunService);
    }

    @Bean
    public StageRunConnector stageRunConnector(StageRunEventProcessor stageRunEventProcessor,
                                               StageRunDependencyAdapter stageRunDependencyAdapter) {
        return new StageRunConnectorImpl(stageRunDependencyAdapter, stageRunEventProcessor);
    }

    @Configuration
    public static class StageRunEventProcessorsConfig {
        @Bean
        public AcknowledgeStageEventProcessor acknowledgeStageEventProcessor(StageRunService stageRunService,
                                                                     FlowRunService flowRunService) {
            return new AcknowledgeStageEventProcessor(flowRunService, stageRunService);
        }

        @Bean
        public StartRunStageEventProcessor startRunStageEventProcessor(StageRunService stageRunService,
                                                                  FlowRunService flowRunService) {
            return new StartRunStageEventProcessor(flowRunService, stageRunService);
        }

        @Bean
        public RunFailedStageEventProcessor runFailedStageEventProcessor(StageRunService stageRunService,
                                                                   FlowRunService flowRunService) {
            return new RunFailedStageEventProcessor(flowRunService, stageRunService);
        }

        @Bean
        public RunCancelledStageEventProcessor runCancelledStageEventProcessor(FlowRunService flowRunService,
                                                                      StageRunService stageRunService) {
            return new RunCancelledStageEventProcessor(flowRunService, stageRunService);
        }

        @Bean
        public RunSuccessfulStageEventProcessor runSuccessfulStageEventProcessor(StageRunService stageRunService,
                                                                       FlowRunService flowRunService) {
            return new RunSuccessfulStageEventProcessor(flowRunService, stageRunService);
        }
    }
}
