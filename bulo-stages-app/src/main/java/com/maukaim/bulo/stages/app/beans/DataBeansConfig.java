package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.stages.io.StageUpdateEventPublisher;
import com.maukaim.bulo.stages.models.TechnicalStageDefinitionStore;
import com.maukaim.bulo.stages.models.definition.ParameterDefinition;
import com.maukaim.bulo.stages.models.definition.TechnicalStageDefinition;
import com.maukaim.bulo.stages.models.stage.Parameter;
import com.maukaim.bulo.stages.persistence.StageStoreImpl;
import com.maukaim.bulo.stages.persistence.TechnicalStageDefinitionStoreImpl;
import com.maukaim.bulo.stages.persistence.adapters.StageDtoAdapter;
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
    public TechnicalStageDefinitionStore technicalStageDefinitionStore() {
        return new TechnicalStageDefinitionStoreImpl(new HashMap<>(
                Map.of("DEFINITION_1",
                        new TechnicalStageDefinition("DEFINITION_1",
                                List.of(
                                        new ParameterDefinition(
                                                "Param", "java.lang.String",
                                                "Hint",
                                                "Description",
                                                true
                                        )
                                )))
        ));
    }
}
