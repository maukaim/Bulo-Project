package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.ms.connectivity.SystemConnector;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.FlowEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.FlowRunEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.StageRunEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.TriggerEventConsumerImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.FlowRunEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.NeedStageRunCancellationEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.NeedStageRunExecutionEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.StageRunEventService;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.FlowRunStoreImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.flows.FlowAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.runs.flow.FlowRunAdapter;
import com.maukaim.bulo.runs.orchestrators.io.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IOBeansConfig {

    @Bean
    public FlowRunEventConsumer flowRunEventConsumer(FlowRunStoreImpl flowRunStoreImpl,
                                                     FlowRunAdapter flowRunAdapter) {
        return new FlowRunEventConsumerImpl(flowRunStoreImpl, flowRunAdapter);
    }

    @Bean
    public TriggerEventConsumer triggerEventConsumer(FlowRunService flowRunService) {
        return new TriggerEventConsumerImpl(flowRunService);
    }

    @Bean
    public StageRunEventConsumer stageRunEventConsumer(StageRunEventService stageRunEventService) {
        return new StageRunEventConsumerImpl(stageRunEventService);
    }

    @Bean
    public SystemConnector systemConnector(){
        return new SystemConnector(new RestTemplate());
    }
    @Bean
    public FlowRunEventPublisher flowRunEventPublisher(SystemConnector systemConnector) {
        return new FlowRunEventPublisherImpl(systemConnector);
    }

    @Bean
    public FlowEventConsumer flowEventConsumer(FlowAdapter flowAdapter,
                                               FlowStore flowStore) {
        return new FlowEventConsumerImpl(flowAdapter, flowStore);
    }

    @Bean
    public NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher(SystemConnector systemConnector) {
        return new NeedStageRunExecutionEventPublisherImpl(systemConnector);
    }

    @Bean
    public NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher(SystemConnector systemConnector) {
        return new NeedStageRunCancellationEventPublisherImpl(systemConnector);
    }
}
