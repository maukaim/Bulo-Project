package com.maukaim.bulo.executors.app.beans;

import com.maukaim.bulo.executors.app.io.DummyStageDefinitionDeclarationEventPublisher;
import com.maukaim.bulo.executors.app.io.DummyStageRunEventPublisher;
import com.maukaim.bulo.executors.io.StageDefinitionDeclarationEventPublisher;
import com.maukaim.bulo.executors.io.StageRunEventPublisher;
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
