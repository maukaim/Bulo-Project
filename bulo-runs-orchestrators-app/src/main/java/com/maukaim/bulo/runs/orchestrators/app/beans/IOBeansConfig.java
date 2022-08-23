package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.app.io.consumers.FlowRunEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.StageRunEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.TriggerEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.DummyFlowRunEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.DummyNeedStageRunCancellationEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.DummyNeedStageRunExecutionEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunEventService;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.FlowRunStoreImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.FlowRunAdapter;
import com.maukaim.bulo.runs.orchestrator.io.*;
import com.maukaim.bulo.runs.orchestrators.io.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOBeansConfig {

    @Bean
    public FlowRunEventConsumer flowRunEventConsumer(FlowRunStoreImpl flowRunStoreImpl,
                                                     FlowRunAdapter flowRunAdapter) {
        return new FlowRunEventConsumerImpl(flowRunStoreImpl, flowRunAdapter);
    }

    @Bean
    public TriggerEventConsumer triggerEventConsumer(FlowRunService flowRunService){
        return new TriggerEventConsumerImpl(flowRunService);
    }

    @Bean
    public StageRunEventConsumer stageRunEventConsumer(StageRunEventService stageRunEventService){
        return new StageRunEventConsumerImpl(stageRunEventService);
    }

    @Bean
    public FlowRunEventPublisher flowRunUpdatePublisher() {
        return new DummyFlowRunEventPublisherImpl();
    }

    @Bean
    public NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher() {
        return new DummyNeedStageRunExecutionEventPublisherImpl();
    }

    @Bean
    public NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher() {
        return new DummyNeedStageRunCancellationEventPublisherImpl();
    }
}
