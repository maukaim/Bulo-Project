package com.maukaim.bulo.runs.orchestrator.app.beans;

import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageEventManager;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunCache;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunService;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.processors.AcknowledgeStageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.processors.RunCancelledStageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.processors.RunFailedStageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.processors.RunSuccessfulStageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.processors.StartRunStageEventProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ProcessorBeansConfig {

    @Bean
    public StageEventManager stageEventManager(Set<StageEventProcessor<?>> processors, StageRunCache stageRunCache) {
        return new StageEventManager(processors, stageRunCache);
    }

    @Bean
    public StageEventProcessor<?> acknowledgeStageEventProcessor(StageRunService stageRunService,
                                                                 FlowRunService flowRunService) {
        return new AcknowledgeStageEventProcessor(flowRunService, stageRunService);
    }

    @Bean
    public StageEventProcessor<?> startRunStageEventProcessor(StageRunService stageRunService,
                                                              FlowRunService flowRunService) {
        return new StartRunStageEventProcessor(flowRunService, stageRunService);
    }

    @Bean
    public StageEventProcessor<?> runFailedStageEventProcessor(StageRunService stageRunService,
                                                               FlowRunService flowRunService) {
        return new RunFailedStageEventProcessor(flowRunService, stageRunService);
    }

    @Bean
    public StageEventProcessor<?> runCancelledStageEventProcessor(FlowRunService flowRunService) {
        return new RunCancelledStageEventProcessor(flowRunService);
    }

    @Bean
    public StageEventProcessor<?> runSuccessfulStageEventProcessor(StageRunService stageRunService,
                                                                   FlowRunService flowRunService) {
        return new RunSuccessfulStageEventProcessor(flowRunService, stageRunService);
    }
}
