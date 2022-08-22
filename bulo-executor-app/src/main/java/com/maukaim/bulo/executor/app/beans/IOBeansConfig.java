package com.maukaim.bulo.executor.app.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maukaim.bulo.executor.app.io.DummyStageDefinitionDeclarationEventPublisher;
import com.maukaim.bulo.executor.app.io.DummyStageRunEventPublisher;
import com.maukaim.bulo.executor.io.StageDefinitionDeclarationEventPublisher;
import com.maukaim.bulo.executor.io.StageRunEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOBeansConfig {

    @Bean
    public StageDefinitionDeclarationEventPublisher technicalStageDefinitionDeclarationEventPublisher(){
        return new DummyStageDefinitionDeclarationEventPublisher();
    }

    @Bean public StageRunEventPublisher stageRunEventPublisher(){
        return new DummyStageRunEventPublisher();
    }
}
