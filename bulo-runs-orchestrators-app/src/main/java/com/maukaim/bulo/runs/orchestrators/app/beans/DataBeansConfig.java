package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.app.data.FakeContextProvider;
import com.maukaim.bulo.runs.orchestrators.data.FlowStore;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.FlowStoreImpl;
import com.maukaim.bulo.runs.orchestrators.data.StageRunConnector;
import com.maukaim.bulo.runs.orchestrators.data.StageRunStore;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.StageRunConnectorImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.StageRunStoreImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.FlowRunStoreImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.FlowRunAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.FlowRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.StageRunAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.StageRunDtoAdapter;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.impl.FlowRunAdapterImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.impl.FlowRunDtoAdapterImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.impl.StageRunAdapterImpl;
import com.maukaim.bulo.runs.orchestrators.data.lifecycle.adapters.impl.StageRunDtoAdapterImpl;
import com.maukaim.bulo.runs.orchestrators.io.FlowRunEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunCancellationEventPublisher;
import com.maukaim.bulo.runs.orchestrators.io.NeedStageRunExecutionEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBeansConfig {

    @Bean
    public FlowStore flowViewCacheImpl() {
        FlowStoreImpl flowViewCache = new FlowStoreImpl();
        flowViewCache.put(FakeContextProvider.FLOW_1.getFlowId(), FakeContextProvider.FLOW_1);
        return flowViewCache;
    }

    @Bean
    public StageRunStore stageRunCache() {
        return new StageRunStoreImpl();
    }

    @Bean
    public FlowRunStoreImpl flowRunStoreImpl(FlowRunEventPublisher flowRunEventPublisher,
                                     FlowRunDtoAdapter flowRunDtoAdapter) {
        return new FlowRunStoreImpl(flowRunEventPublisher, flowRunDtoAdapter);
    }

    @Bean
    public FlowRunDtoAdapter flowRunDtoAdapter(StageRunDtoAdapter stageRunDtoAdapter) {
        return new FlowRunDtoAdapterImpl(stageRunDtoAdapter);
    }

    @Bean
    public StageRunDtoAdapter stageRunDtoAdapter(){
        return new StageRunDtoAdapterImpl();
    }

    @Bean
    public FlowRunAdapter flowRunAdapter(StageRunAdapter stageRunAdapter){
        return new FlowRunAdapterImpl(stageRunAdapter);
    }

    @Bean StageRunAdapter stageRunAdapter(){
        return new StageRunAdapterImpl();
    }

    @Bean
    public StageRunConnector stageRunConnector(NeedStageRunCancellationEventPublisher needStageRunCancellationEventPublisher,
                                               NeedStageRunExecutionEventPublisher needStageRunExecutionEventPublisher){
        return new StageRunConnectorImpl(needStageRunExecutionEventPublisher, needStageRunCancellationEventPublisher);
    }
}
