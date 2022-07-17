package com.maukaim.bulo.runs.orchestrator.app.beans;

import com.maukaim.bulo.runs.orchestrator.app.data.FakeContextProvider;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunCache;
import com.maukaim.bulo.runs.orchestrator.core.flowrun.FlowRunCacheImpl;
import com.maukaim.bulo.runs.orchestrator.core.flow.FlowCache;
import com.maukaim.bulo.runs.orchestrator.core.flow.FlowCacheImpl;
import com.maukaim.bulo.runs.orchestrator.core.FlowRunEventPublisher;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunCache;
import com.maukaim.bulo.runs.orchestrator.core.stagerun.StageRunCacheImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheBeansConfig {

    @Bean
    public FlowCache flowViewCacheImpl(){
        FlowCacheImpl flowViewCache = new FlowCacheImpl();
        flowViewCache.put(FakeContextProvider.FLOW_1.getFlowId(), FakeContextProvider.FLOW_1);
        return flowViewCache;
    }

    @Bean
    public FlowRunCache flowRunCache(FlowRunEventPublisher flowRunEventPublisher){
        return new FlowRunCacheImpl(flowRunEventPublisher);
    }

    @Bean
    public StageRunCache stageRunCache(){
        return new StageRunCacheImpl();
    }
}
