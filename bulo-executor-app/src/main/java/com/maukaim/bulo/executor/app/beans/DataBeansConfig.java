package com.maukaim.bulo.executor.app.beans;

import com.maukaim.bulo.executor.core.TechnicalStageRunnerManager;
import com.maukaim.bulo.executor.core.api.TechnicalStageDefinitionStore;
import com.maukaim.bulo.executor.core.api.models.TechnicalStageDefinition;
import com.maukaim.bulo.executor.data.management.TechnicalStageDefinitionStoreImpl;
import com.maukaim.bulo.executor.data.management.adapters.TechnicalStageDefinitionDtoAdapter;
import com.maukaim.bulo.executor.io.TsdDeclarationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataBeansConfig {

    @Bean
    public TechnicalStageDefinitionStore technicalStageDefinitionStore(TechnicalStageRunnerManager runnerManager,
                                                                       TechnicalStageDefinitionDtoAdapter technicalStageDefinitionDtoAdapter,
                                                                       TsdDeclarationEventPublisher eventPublisher) {
        List<TechnicalStageDefinition> allDefinitions = runnerManager.getAllDefinitions();
        TechnicalStageDefinitionStoreImpl technicalStageDefinitionStore = new TechnicalStageDefinitionStoreImpl(technicalStageDefinitionDtoAdapter, eventPublisher);
        technicalStageDefinitionStore.putAll(allDefinitions);
        return technicalStageDefinitionStore;
    }
}
