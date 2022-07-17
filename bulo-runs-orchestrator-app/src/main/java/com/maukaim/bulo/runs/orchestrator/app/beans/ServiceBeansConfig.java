package com.maukaim.bulo.runs.orchestrator.app.beans;

import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.core.flow.FlowService;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunCache;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunServiceImpl;
import com.maukaim.bulo.runs.orchestrator.core.flow.FlowCache;
import com.maukaim.bulo.runs.orchestrator.core.flow.FlowServiceImpl;
import com.maukaim.bulo.runs.orchestrator.core.StageRunEventPublisher;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunCache;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunService;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeansConfig {

    @Bean
    public FlowService flowServiceImpl(FlowCache flowCache){
        return new FlowServiceImpl(flowCache);
    }

    @Bean
    public FlowRunService flowRunServiceImpl(FlowService flowService,
                                             FlowRunCache flowRunCache,
                                             StageRunService stageRunService){
        return new FlowRunServiceImpl(flowService, flowRunCache, stageRunService);
    }

    @Bean
    public StageRunService stageRunService(StageRunCache stageRunCache, StageRunEventPublisher eventPublisher){
        return new StageRunServiceImpl(eventPublisher, stageRunCache);
    }
}
