package com.maukaim.bulo.standalone.app.beans.modules;

import com.maukaim.bulo.executors.core.StageRunProcessor;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.FlowService;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.factories.FlowRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.impl.AcknowledgeStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.FlowRunServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.impl.FlowServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.impl.FunctionalStageDefinitionServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.impl.FunctionalStageServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunCancelledStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunFailedStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunSuccessfulStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.StageRunServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.impl.StartRunStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.utils.FlowUtils;
import com.maukaim.bulo.runs.orchestrators.core.utils.OrchestrableContextStatusResolver;
import com.maukaim.bulo.runs.orchestrators.core.utils.OrchestrableUtils;
import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageDefinitionStore;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.standalone.app.connectivity.StageRunConnectorImpl;
import com.maukaim.bulo.standalone.data.lifecycle.StageRunResultListener;
import com.maukaim.bulo.standalone.data.lifecycle.runs.adapters.StageRunDependencyAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                                           FunctionalStageDefinitionService functionalStageDefinitionService,
                                           TechnicalStageRunFactory technicalStageRunFactory,
                                           FunctionalStageRunFactory functionalStageRunFactory,
                                           OrchestrableContextStatusResolver orchestrableContextStatusResolver) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        return new StageRunServiceImpl(stageRunConnector, stageRunStore, executorService, functionalStageService, functionalStageDefinitionService,
                functionalStageRunFactory, technicalStageRunFactory, orchestrableContextStatusResolver);
    }

    @Bean
    public FlowRunService flowRunService(FlowService flowService,
                                         StageRunService stageRunService,
                                         FlowRunStore flowRunStore,
                                         FlowUtils flowUtils,
                                         FlowRunFactory flowRunFactory,
                                         OrchestrableContextStatusResolver orchestrableContextStatusResolver) {
        return new FlowRunServiceImpl(flowService, flowRunStore, stageRunService, flowUtils, flowRunFactory,orchestrableContextStatusResolver);
    }

    @Bean
    public StageRunConnectorImpl stageRunConnector(StageRunProcessor stageRunProcessor,
                                                   StageRunDependencyAdapter stageRunDependencyAdapter) {
        return new StageRunConnectorImpl(stageRunDependencyAdapter, stageRunProcessor);
    }

    @Bean
    public FunctionalStageService functionalStageService(FunctionalStageStore functionalStageStore) {
        return new FunctionalStageServiceImpl(functionalStageStore);
    }

    @Bean
    public FunctionalStageDefinitionService functionalStageDefinitionService(FunctionalStageDefinitionStore definitionStore) {
        return new FunctionalStageDefinitionServiceImpl(definitionStore);
    }

    @Bean
    public FlowUtils flowUtils() {
        return new FlowUtils();
    }

    @Bean
    public FlowRunFactory flowRunFactory() {
        return new FlowRunFactory();
    }

    @Bean
    public FunctionalStageRunFactory functionalStageRunFactory() {
        return new FunctionalStageRunFactory();
    }

    @Bean
    public TechnicalStageRunFactory technicalStageRunFactory() {
        return new TechnicalStageRunFactory();
    }

    @Bean
    public OrchestrableContextStatusResolver orchestrableContextStatusResolver(){
        return new OrchestrableContextStatusResolver();
    }

    @Bean
    public OrchestrableUtils orchestrableUtils(){
        return new OrchestrableUtils();
    }

    @Configuration
    public static class StageRunEventProcessorsConfig {
        @Bean
        public AcknowledgeStageRunEventProcessor acknowledgeStageEventProcessor(StageRunService stageRunService,
                                                                                FlowRunService flowRunService,
                                                                                TechnicalStageRunFactory technicalStageRunFactory,
                                                                                FunctionalStageRunFactory functionalStageRunFactory) {
            return new AcknowledgeStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
        }

        @Bean
        public StartRunStageRunEventProcessor startRunStageEventProcessor(StageRunService stageRunService,
                                                                          FlowRunService flowRunService,
                                                                          TechnicalStageRunFactory technicalStageRunFactory,
                                                                          FunctionalStageRunFactory functionalStageRunFactory) {
            return new StartRunStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
        }

        @Bean
        public RunFailedStageRunEventProcessor runFailedStageEventProcessor(StageRunService stageRunService,
                                                                            FlowRunService flowRunService,
                                                                            TechnicalStageRunFactory technicalStageRunFactory,
                                                                            FunctionalStageRunFactory functionalStageRunFactory) {
            return new RunFailedStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
        }

        @Bean
        public RunCancelledStageRunEventProcessor runCancelledStageEventProcessor(FlowRunService flowRunService,
                                                                                  StageRunService stageRunService,
                                                                                  TechnicalStageRunFactory technicalStageRunFactory,
                                                                                  FunctionalStageRunFactory functionalStageRunFactory) {
            return new RunCancelledStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
        }

        @Bean
        public RunSuccessfulStageRunEventProcessor runSuccessfulStageEventProcessor(StageRunService stageRunService,
                                                                                    FlowRunService flowRunService,
                                                                                    TechnicalStageRunFactory technicalStageRunFactory,
                                                                                    FunctionalStageRunFactory functionalStageRunFactory,
                                                                                    OrchestrableUtils orchestrableUtils) {
            return new RunSuccessfulStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory, orchestrableUtils);
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
