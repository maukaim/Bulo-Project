package com.maukaim.bulo.executors.app.beans;

import com.maukaim.bulo.executors.app.data.FakeDataProvider;
import com.maukaim.bulo.executors.core.StageRunnerRegistry;
import com.maukaim.bulo.executors.data.StageDefinitionStore;
import com.maukaim.bulo.executors.data.StageStore;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageRunResultDtoAdapter;
import com.maukaim.bulo.runners.api.models.StageDefinition;
import com.maukaim.bulo.executors.data.lifecycle.StageDefinitionStoreImpl;
import com.maukaim.bulo.executors.data.lifecycle.StageRunResultStoreImpl;
import com.maukaim.bulo.executors.data.lifecycle.StageStoreImpl;
import com.maukaim.bulo.executors.data.lifecycle.adapters.StageDefinitionDtoAdapter;
import com.maukaim.bulo.executors.data.lifecycle.resolver.StageRunEventResolver;
import com.maukaim.bulo.executors.data.lifecycle.resolver.StageRunEventResolverImpl;
import com.maukaim.bulo.executors.io.StageDefinitionCreateInstructionPublisher;
import com.maukaim.bulo.executors.io.StageRunEventPublisher;
import com.maukaim.bulo.executors.io.StageRunResultEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class DataBeansConfig {

    @Bean
    public StageDefinitionStore stageDefinitionStore(StageRunnerRegistry runnerRegistry,
                                                              StageDefinitionDtoAdapter stageDefinitionDtoAdapter,
                                                              StageDefinitionCreateInstructionPublisher eventPublisher) {
        List<StageDefinition> allDefinitions = runnerRegistry.getAllDefinitions();
        StageDefinitionStoreImpl stageDefinitionStore = new StageDefinitionStoreImpl(
                stageDefinitionDtoAdapter,
                eventPublisher,
                Map.of());
        stageDefinitionStore.putAll(allDefinitions);
        return stageDefinitionStore;
    }

    @Bean
    public StageRunResultStoreImpl runResultStore(StageRunEventPublisher stageRunEventPublisher,
                                              StageRunEventResolver runEventResolver,
                                              StageRunResultDtoAdapter stageRunResultDtoAdapter,
                                              StageRunResultEventPublisher stageRunResultEventPublisher) {
        return new StageRunResultStoreImpl(Map.of(), stageRunEventPublisher, runEventResolver, stageRunResultEventPublisher, stageRunResultDtoAdapter);
    }

    @Bean StageRunEventResolver stageRunEventResolver(){
        return new StageRunEventResolverImpl();
    }

    @Bean
    public StageStore stageStore(){
        return new StageStoreImpl(
                Map.of(FakeDataProvider.STAGE_WITH_NO_INPUT_ID, FakeDataProvider.STAGE_WITH_NO_INPUT,
                        FakeDataProvider.STAGE_WITH_INPUT_ID, FakeDataProvider.STAGE_WITH_INPUT)
        );
    }
}
