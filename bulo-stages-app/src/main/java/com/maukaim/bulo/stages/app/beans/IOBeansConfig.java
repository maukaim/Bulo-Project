package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.io.StageUpdateEventConsumer;
import com.maukaim.bulo.io.StageUpdateEventPublisher;
import com.maukaim.bulo.stages.app.io.DummyStageUpdateEventPublisher;
import com.maukaim.bulo.stages.app.io.StageUpdateEventConsumerImpl;
import com.maukaim.bulo.stages.core.StageAdapter;
import com.maukaim.bulo.stages.persistence.StageStoreImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOBeansConfig {

    @Bean
    public StageUpdateEventConsumer stageUpdateEventConsumer(StageStoreImpl stageStore,
                                                             StageAdapter stageAdapter) {
        return new StageUpdateEventConsumerImpl(stageStore, stageAdapter);
    }

    @Bean
    public StageUpdateEventPublisher stageUpdateEventPublisher() {
        return new DummyStageUpdateEventPublisher();
    }
}
