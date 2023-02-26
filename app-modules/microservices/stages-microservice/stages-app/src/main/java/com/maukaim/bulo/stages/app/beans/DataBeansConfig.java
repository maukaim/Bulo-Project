package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.api.data.types.natives.StringType;
import com.maukaim.bulo.stages.data.lifecycle.StageDtoAdapter;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageUpdateEventPublisher;
import com.maukaim.bulo.stages.models.StageDefinitionStore;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;
import com.maukaim.bulo.stages.models.definition.StageDefinition;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageDefinitionStoreImpl;
import com.maukaim.bulo.stages.ms.data.lifecycle.StageStoreImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DataBeansConfig {
    @Bean
    public StageStoreImpl stageStore(StageDtoAdapter stageDtoAdapter,
                                     StageUpdateEventPublisher publisher) {
        return new StageStoreImpl(
                new HashMap<>(),
                publisher,
                stageDtoAdapter);
    }

    @Bean
    public StageDefinitionStore technicalStageDefinitionStore() {
        return new StageDefinitionStoreImpl(new HashMap<>(
                Map.of("DEFINITION_1",
                        new StageDefinition("DEFINITION_1",
                                List.of(
                                        new ParameterDefinition(
                                                "Param", StringType.required(),
                                                "Hint",
                                                "Description"
                                        )
                                )))
        ));
    }
}
