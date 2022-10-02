package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorBeansConfig {
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
