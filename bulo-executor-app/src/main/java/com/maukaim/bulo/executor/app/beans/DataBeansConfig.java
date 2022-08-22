package com.maukaim.bulo.executor.app.beans;

import com.maukaim.bulo.executor.core.StageRunnerRegistry;
import com.maukaim.bulo.executor.core.api.StageDefinitionStore;
import com.maukaim.bulo.executor.core.api.StageRunResultStore;
import com.maukaim.bulo.executor.core.api.StageStore;
import com.maukaim.bulo.executor.core.api.models.StageDefinition;
import com.maukaim.bulo.executor.data.management.StageDefinitionStoreImpl;
import com.maukaim.bulo.executor.data.management.StageRunResultStoreImpl;
import com.maukaim.bulo.executor.data.management.StageStoreImpl;
import com.maukaim.bulo.executor.data.management.adapters.StageDefinitionDtoAdapter;
import com.maukaim.bulo.executor.data.management.resolver.StageRunEventResolver;
import com.maukaim.bulo.executor.data.management.resolver.StageRunEventResolverImpl;
import com.maukaim.bulo.executor.io.StageDefinitionDeclarationEventPublisher;
import com.maukaim.bulo.executor.io.StageRunEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.maukaim.bulo.executor.app.data.FakeDataProvider.*;

@Configuration
public class DataBeansConfig {

    @Bean
    public StageDefinitionStore technicalStageDefinitionStore(StageRunnerRegistry runnerRegistry,
                                                              StageDefinitionDtoAdapter stageDefinitionDtoAdapter,
                                                              StageDefinitionDeclarationEventPublisher eventPublisher) {
        List<StageDefinition> allDefinitions = runnerRegistry.getAllDefinitions();
        StageDefinitionStoreImpl technicalStageDefinitionStore = new StageDefinitionStoreImpl(
                stageDefinitionDtoAdapter,
                eventPublisher,
                Map.of());
        technicalStageDefinitionStore.putAll(allDefinitions);
        return technicalStageDefinitionStore;
    }

    @Bean
    public StageRunResultStore runResultStore(StageRunEventPublisher stageRunEventPublisher,
                                              StageRunEventResolver runEventResolver) {
        return new StageRunResultStoreImpl(Map.of(), stageRunEventPublisher, runEventResolver);
    }

    @Bean StageRunEventResolver stageRunEventResolver(){
        return new StageRunEventResolverImpl();
    }

    @Bean
    public StageStore stageStore(){
        return new StageStoreImpl(
                Map.of(STAGE_WITH_NO_INPUT_ID, STAGE_WITH_NO_INPUT,
                        STAGE_WITH_INPUT_ID, STAGE_WITH_INPUT)
        );
    }
}
