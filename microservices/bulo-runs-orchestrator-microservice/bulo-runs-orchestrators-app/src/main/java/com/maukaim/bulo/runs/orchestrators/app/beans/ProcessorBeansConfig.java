package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunEventService;
import com.maukaim.bulo.runs.orchestrators.core.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.StageRunEventServiceImpl;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.impl.AcknowledgeStageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunCancelledStageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunFailedStageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.RunSuccessfulStageEventProcessor;
import com.maukaim.bulo.runs.orchestrators.core.impl.StartRunStageEventProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ProcessorBeansConfig {

    @Bean
    public StageRunEventService stageEventManager(Set<StageEventProcessor<?>> processors, StageRunStore stageRunStore) {
        return new StageRunEventServiceImpl(processors, stageRunStore);
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
