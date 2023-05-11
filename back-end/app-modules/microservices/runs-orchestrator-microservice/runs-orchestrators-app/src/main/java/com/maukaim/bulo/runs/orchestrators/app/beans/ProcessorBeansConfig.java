package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorBeansConfig {
    @Bean
    public AcknowledgeTechnicalStageRunEventProcessor acknowledgeStageEventProcessor(StageRunService stageRunService,
                                                                                     FlowRunService flowRunService) {
        return new AcknowledgeTechnicalStageRunEventProcessor(flowRunService, stageRunService);
    }

    @Bean
    public StartRunTechnicalStageRunEventProcessor startRunStageEventProcessor(StageRunService stageRunService,
                                                                               FlowRunService flowRunService) {
        return new StartRunTechnicalStageRunEventProcessor(flowRunService, stageRunService);
    }

    @Bean
    public RunFailedTechnicalStageRunEventProcessor runFailedStageEventProcessor(StageRunService stageRunService,
                                                                                 FlowRunService flowRunService) {
        return new RunFailedTechnicalStageRunEventProcessor(flowRunService, stageRunService);
    }

    @Bean
    public RunCancelledTechnicalStageRunEventProcessor runCancelledStageEventProcessor(FlowRunService flowRunService,
                                                                                       StageRunService stageRunService) {
        return new RunCancelledTechnicalStageRunEventProcessor(flowRunService, stageRunService);
    }

    @Bean
    public RunSuccessfulTechnicalStageRunEventProcessor runSuccessfulStageEventProcessor(StageRunService stageRunService,
                                                                                         FlowRunService flowRunService) {
        return new RunSuccessfulTechnicalStageRunEventProcessor(flowRunService, stageRunService);
    }
}
