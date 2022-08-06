package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.io.StageUpdateEventPublisher;
import com.maukaim.bulo.stages.models.TechnicalStageDefinitionStore;
import com.maukaim.bulo.stages.persistence.StageStoreImpl;
import com.maukaim.bulo.stages.persistence.TechnicalStageDefinitionStoreImpl;
import com.maukaim.bulo.stages.persistence.adapters.StageDataAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class DataBeansConfig {
    @Bean
    public StageStoreImpl stageStore(StageDataAdapter stageDataAdapter,
                                     StageUpdateEventPublisher publisher) {
        return new StageStoreImpl(
                new HashMap<>(),
                publisher,
                stageDataAdapter);
    }

    @Bean
    public TechnicalStageDefinitionStore technicalStageDefinitionStore() {
        return new TechnicalStageDefinitionStoreImpl(new HashMap<>());
    }
}
