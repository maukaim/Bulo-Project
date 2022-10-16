package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.data.lifecycle.TechnicalStageDefinitionStoreImpl;
import com.maukaim.bulo.definitions.data.lifecycle.adapters.TechnicalStageDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.io.TechnicalStageDefinitionEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBeansConfig {
    @Bean
    public TechnicalStageDefinitionStoreImpl definitionStore(TechnicalStageDefinitionEventPublisher technicalStageDefinitionEventPublisher,
                                                             TechnicalStageDefinitionDtoAdapter technicalStageDefinitionDtoAdapter) {
        return new TechnicalStageDefinitionStoreImpl(technicalStageDefinitionEventPublisher, technicalStageDefinitionDtoAdapter);
    }
}
