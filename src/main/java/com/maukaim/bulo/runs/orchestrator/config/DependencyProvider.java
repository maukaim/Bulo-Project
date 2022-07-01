package com.maukaim.bulo.runs.orchestrator.config;

import com.maukaim.bulo.runs.orchestrator.flow.FlowViewService;
import com.maukaim.bulo.runs.orchestrator.flow.run.FlowRunCache;
import com.maukaim.bulo.runs.orchestrator.flow.run.FlowRunCacheImpl;
import com.maukaim.bulo.runs.orchestrator.flow.run.FlowRunServiceImpl;
import com.maukaim.bulo.runs.orchestrator.flow.view.FlowViewCache;
import com.maukaim.bulo.runs.orchestrator.flow.view.FlowViewCacheImpl;
import com.maukaim.bulo.runs.orchestrator.flow.view.FlowViewServiceImpl;
import com.maukaim.bulo.runs.orchestrator.publisher.FlowRunUpdatePublisher;
import com.maukaim.bulo.runs.orchestrator.publisher.StageRunEventPublisher;
import com.maukaim.bulo.runs.orchestrator.publisher.impl.DummyFlowRunUpdatePublisherImpl;
import com.maukaim.bulo.runs.orchestrator.publisher.impl.DummyStageRunEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrator.flow.FlowRunService;
import com.maukaim.bulo.runs.orchestrator.stage.run.impl.StageRunCacheImpl;
import com.maukaim.bulo.runs.orchestrator.stage.run.impl.StageRunServiceImpl;
import com.maukaim.bulo.runs.orchestrator.stage.run.StageEventManager;
import com.maukaim.bulo.runs.orchestrator.stage.run.StageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.stage.run.StageRunCache;
import com.maukaim.bulo.runs.orchestrator.stage.run.StageRunService;
import com.maukaim.bulo.runs.orchestrator.stage.run.processors.AcknowledgeStageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.stage.run.processors.RunCancelledStageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.stage.run.processors.RunFailedStageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.stage.run.processors.RunSuccessfulStageEventProcessor;
import com.maukaim.bulo.runs.orchestrator.stage.run.processors.StartRunStageEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

import static com.maukaim.bulo.runs.orchestrator.config.FakeContextProvider.FLOW_1;

@Configuration
public class DependencyProvider {

    @Autowired
    private FlowRunService flowRunService;

    @Bean
    public FlowRunUpdatePublisher flowRunUpdatePublisher(){
        return new DummyFlowRunUpdatePublisherImpl();
    }

    @Bean
    public StageRunEventPublisher stageRunEventPublisher(){
        return new DummyStageRunEventPublisherImpl();
    }

    @Bean
    public FlowViewService flowServiceImpl(FlowViewCache flowViewCache){
        return new FlowViewServiceImpl(flowViewCache);
    }

    @Bean
    public FlowRunService flowRunServiceImpl(FlowViewService flowViewService,
                                             FlowRunCache flowRunCache,
                                             StageRunService stageRunService){
        return new FlowRunServiceImpl(flowViewService, flowRunCache, stageRunService);
    }

    @Bean
    public FlowViewCache flowViewCacheImpl(){
        FlowViewCacheImpl flowViewCache = new FlowViewCacheImpl();
        flowViewCache.put(FLOW_1.getFlowId(), FLOW_1);
        return flowViewCache;
    }

    @Bean
    public FlowRunCache flowRunCache(FlowRunUpdatePublisher flowRunUpdatePublisher){
        return new FlowRunCacheImpl(flowRunUpdatePublisher);
    }

    @Bean
    public StageRunCache stageRunCache(){
        return new StageRunCacheImpl();
    }

    @Bean
    public StageRunService stageRunService(StageRunCache stageRunCache, StageRunEventPublisher eventPublisher){
        return new StageRunServiceImpl(eventPublisher, stageRunCache);
    }

    @Bean
    public StageEventManager stageEventManager(Set<StageEventProcessor<?>> processors, StageRunCache stageRunCache) {
        return new StageEventManager(processors, stageRunCache);
    }

    @Bean
    public StageEventProcessor<?> acknowledgeStageEventProcessor(StageRunService stageRunService){
        return new AcknowledgeStageEventProcessor(flowRunService, stageRunService);
    }

    @Bean
    public StageEventProcessor<?> startRunStageEventProcessor(StageRunService stageRunService){
        return new StartRunStageEventProcessor(flowRunService, stageRunService);
    }

    @Bean
    public StageEventProcessor<?> runFailedStageEventProcessor(StageRunService stageRunService){
        return new RunFailedStageEventProcessor(flowRunService, stageRunService);
    }

    @Bean
    public StageEventProcessor<?> runCancelledStageEventProcessor(){
        return new RunCancelledStageEventProcessor(flowRunService);
    }

    @Bean
    public StageEventProcessor<?> runSuccessfulStageEventProcessor(StageRunService stageRunService){
        return new RunSuccessfulStageEventProcessor(flowRunService, stageRunService);
    }
}
