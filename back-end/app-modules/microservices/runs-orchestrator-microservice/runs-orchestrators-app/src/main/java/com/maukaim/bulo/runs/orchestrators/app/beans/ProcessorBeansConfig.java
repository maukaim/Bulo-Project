package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.impl.AcknowledgeStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunCancelledStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunFailedStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunSuccessfulStageRunEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.StartRunStageRunEventProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorBeansConfig {
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
                                                                                FunctionalStageRunFactory functionalStageRunFactory) {
        return new RunSuccessfulStageRunEventProcessor(flowRunService, stageRunService, functionalStageRunFactory, technicalStageRunFactory);
    }
}
