package com.maukaim.boule.flows.orchestrator.config;

import com.maukaim.boule.flows.orchestrator.publisher.FlowRunUpdatePublisher;
import com.maukaim.boule.flows.orchestrator.publisher.StageRunEventPublisher;
import com.maukaim.boule.flows.orchestrator.publisher.impl.DummyFlowRunUpdatePublisherImpl;
import com.maukaim.boule.flows.orchestrator.publisher.impl.DummyStageRunEventPublisherImpl;
import com.maukaim.boule.flows.orchestrator.run.FlowRunService;
import com.maukaim.boule.flows.orchestrator.stage.processors.*;
import com.maukaim.boule.flows.orchestrator.stage.StageEventManager;
import com.maukaim.boule.flows.orchestrator.stage.StageEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

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
    public StageEventManager stageEventManager(Set<StageEventProcessor<?>> processors) {
        return new StageEventManager(processors);
    }

    @Bean
    public StageEventProcessor<?> acknowledgeStageEventProcessor(){
        return new AcknowledgeStageEventProcessor(flowRunService);
    }

    @Bean
    public StageEventProcessor<?> startRunStageEventProcessor(){
        return new StartRunStageEventProcessor(flowRunService);
    }

    @Bean
    public StageEventProcessor<?> runFailedStageEventProcessor(StageRunEventPublisher stageRunEventPublisher){
        return new RunFailedStageEventProcessor(flowRunService, stageRunEventPublisher);
    }

    @Bean
    public StageEventProcessor<?> runCancelledStageEventProcessor(StageRunEventPublisher stageRunEventPublisher){
        return new RunCancelledStageEventProcessor(flowRunService, stageRunEventPublisher);
    }

    @Bean
    public StageEventProcessor<?> runSuccessfulStageEventProcessor(StageRunEventPublisher stageRunEventPublisher){
        return new RunSuccessfulStageEventProcessor(flowRunService, stageRunEventPublisher);
    }
}
