package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.data.lifecycle.StageDefinitionDtoAdapter;
import com.maukaim.bulo.definitions.ms.data.lifecycle.StageDefinitionStoreImpl;
import com.maukaim.bulo.definitions.ms.data.lifecycle.StageStoreImpl;
import com.maukaim.bulo.io.definitions.system.TechnicalStageDefinitionEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DataBeansConfig {
    @Bean
    public StageDefinitionStoreImpl definitionStore(TechnicalStageDefinitionEventPublisher technicalStageDefinitionEventPublisher,
                                                    StageDefinitionDtoAdapter stageDefinitionDtoAdapter) {
        return new StageDefinitionStoreImpl(technicalStageDefinitionEventPublisher, stageDefinitionDtoAdapter);
    }

    @Bean
    public StageStore stageStore(){
        return new StageStoreImpl(Map.of());
    }
}
