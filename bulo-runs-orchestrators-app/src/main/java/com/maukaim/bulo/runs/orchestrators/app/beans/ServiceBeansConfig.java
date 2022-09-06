package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.FlowService;
import com.maukaim.bulo.runs.orchestrators.core.impl.FlowRunServiceImpl;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.core.impl.FlowServiceImpl;
import com.maukaim.bulo.runs.orchestrators.core.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.core.StageRunService;
import com.maukaim.bulo.runs.orchestrators.core.impl.StageRunServiceImpl;
import com.maukaim.bulo.runs.orchestrators.data.FlowRunStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeansConfig {

    @Bean
    public FlowService flowServiceImpl(FlowStore flowStore){
        return new FlowServiceImpl(flowStore);
    }

    @Bean
    public FlowRunService flowRunServiceImpl(FlowService flowService,
                                             FlowRunStore flowRunStore,
                                             StageRunService stageRunService){
        return new FlowRunServiceImpl(flowService, flowRunStore, stageRunService);
    }

    @Bean
    public StageRunService stageRunService(StageRunStore stageRunStore,
                                           StageRunConnector stageRunConnector){
        return new StageRunServiceImpl(stageRunConnector, stageRunStore);
    }
}
