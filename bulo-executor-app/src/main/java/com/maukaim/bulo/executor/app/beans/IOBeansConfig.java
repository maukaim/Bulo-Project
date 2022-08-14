package com.maukaim.bulo.executor.app.beans;

import com.maukaim.bulo.executor.app.io.DummyTsdDeclarationEventPublisher;
import com.maukaim.bulo.executor.io.TsdDeclarationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOBeansConfig {

    @Bean
    public TsdDeclarationEventPublisher technicalStageDefinitionDeclarationEventPublisher(){
        return new DummyTsdDeclarationEventPublisher();
    }
}
