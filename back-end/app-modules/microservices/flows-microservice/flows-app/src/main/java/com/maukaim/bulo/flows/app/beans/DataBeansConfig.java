package com.maukaim.bulo.flows.app.beans;

import com.maukaim.bulo.flows.app.data.FakeProvider;
import com.maukaim.bulo.flows.data.StageDefinitionStore;
import com.maukaim.bulo.flows.data.StageStore;
import com.maukaim.bulo.flows.ms.data.lifecycle.FlowStoreImpl;
import com.maukaim.bulo.flows.ms.data.lifecycle.StageDefinitionStoreImpl;
import com.maukaim.bulo.flows.ms.data.lifecycle.StageStoreImpl;
import com.maukaim.bulo.data.lifecycle.flows.client.FlowDtoAdapter;
import com.maukaim.bulo.flows.ms.data.lifecycle.FlowEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DataBeansConfig {

    @Bean
    public StageStore stageStore() {
        return new StageStoreImpl(Map.of(
                FakeProvider.STAGE_1.getStageId(), FakeProvider.STAGE_1,
                FakeProvider.STAGE_2.getStageId(), FakeProvider.STAGE_2
        ));
    }

    @Bean
    public StageDefinitionStore stageDefinitionStore() {
        return new StageDefinitionStoreImpl(Map.of(
                FakeProvider.STAGE_DEF_1.getStageDefinitionId(), FakeProvider.STAGE_DEF_1,
                FakeProvider.STAGE_DEF_2.getStageDefinitionId(), FakeProvider.STAGE_DEF_2
        ));
    }

    @Bean
    public FlowStoreImpl flowStoreImpl(FlowEventPublisher flowEventPublisher,
                                       FlowDtoAdapter flowDtoAdapter) {
        return new FlowStoreImpl(Map.of(), flowEventPublisher, flowDtoAdapter);
    }
}
