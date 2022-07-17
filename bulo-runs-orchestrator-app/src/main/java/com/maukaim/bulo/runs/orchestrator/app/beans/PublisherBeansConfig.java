package com.maukaim.bulo.runs.orchestrator.app.beans;

import com.maukaim.bulo.runs.orchestrator.core.FlowRunEventPublisher;
import com.maukaim.bulo.runs.orchestrator.core.StageRunEventPublisher;
import com.maukaim.bulo.runs.orchestrator.app.publisher.DummyFlowRunEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrator.app.publisher.DummyStageRunEventPublisherImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherBeansConfig {

    @Bean
    public FlowRunEventPublisher flowRunUpdatePublisher(){
        return new DummyFlowRunEventPublisherImpl();
    }

    @Bean
    public StageRunEventPublisher stageRunEventPublisher(){
        return new DummyStageRunEventPublisherImpl();
    }
}
