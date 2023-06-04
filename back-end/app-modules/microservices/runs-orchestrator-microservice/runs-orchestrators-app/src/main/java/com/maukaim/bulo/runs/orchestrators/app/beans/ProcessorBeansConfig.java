package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorBeansConfig {
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
