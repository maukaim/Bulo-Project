package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.app.shared.system.communication.core.SystemConnector;
import com.maukaim.bulo.ms.shared.system.endpoints.ServiceEventType;
import com.maukaim.bulo.runs.orchestrators.app.io.consumers.*;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.FlowRunEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.NeedStageRunCancellationEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.app.io.publishers.NeedStageRunExecutionEventPublisherImpl;
import com.maukaim.bulo.runs.orchestrators.core.FlowRunService;
import com.maukaim.bulo.runs.orchestrators.core.FunctionalStageDefinitionService;
import com.maukaim.bulo.runs.orchestrators.core.impl.*;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.FunctionalStageStore;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.FlowRunStoreImpl;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.definitions.StageDefinitionAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.flows.FlowAdapter;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.adapters.runs.flow.FlowRunAdapter;
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
    public TriggerEventConsumer triggerEventConsumer(FlowRunService flowRunService) {
        return new TriggerEventConsumerImpl(flowRunService);
    }

    @Bean
    public StageRunEventConsumer stageRunEventConsumer(AcknowledgeTechnicalStageRunEventProcessor acknowledgeStageEventProcessor,
                                                       RunCancelledTechnicalStageRunEventProcessor runCancelledStageEventProcessor,
                                                       RunSuccessfulTechnicalStageRunEventProcessor runSuccessfulStageEventProcessor,
                                                       RunFailedTechnicalStageRunEventProcessor runFailedStageEventProcessor,
                                                       StartRunTechnicalStageRunEventProcessor startRunStageEventProcessor,
                                                       StageRunStore stageRunStore) {
        return new StageRunEventConsumerImpl(acknowledgeStageEventProcessor,
                runCancelledStageEventProcessor,
                runSuccessfulStageEventProcessor,
                runFailedStageEventProcessor,
                startRunStageEventProcessor,
                stageRunStore);
    }

    @Bean
    public FlowRunEventPublisher flowRunEventPublisher(SystemConnector<ServiceEventType> systemConnector) {
        return new FlowRunEventPublisherImpl(systemConnector);
    }

    @Bean
    public FlowEventConsumer flowEventConsumer(FlowAdapter flowAdapter,
                                               FlowStore flowStore) {
        return new FlowEventConsumerImpl(flowAdapter, flowStore);
    }

    @Bean
    public NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher(SystemConnector<ServiceEventType> systemConnector) {
        return new NeedStageRunExecutionEventPublisherImpl(systemConnector);
    }

    @Bean
    public NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher(SystemConnector<ServiceEventType> systemConnector) {
        return new NeedStageRunCancellationEventPublisherImpl(systemConnector);
    }

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(FunctionalStageStore functionalStageStore) {
        return new StageUpdateEventConsumerImpl(functionalStageStore);
    }

    @Bean
    public DefinitionUpdateEventConsumer definitionUpdateEventConsumer(StageDefinitionAdapter stageDefinitionAdapter,
                                                                       FunctionalStageDefinitionService definitionService) {
        return new DefinitionUpdateEventConsumerImpl(stageDefinitionAdapter, definitionService);
    }
}
