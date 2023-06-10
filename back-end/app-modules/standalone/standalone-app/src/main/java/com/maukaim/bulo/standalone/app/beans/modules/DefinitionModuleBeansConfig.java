package com.maukaim.bulo.standalone.app.beans.modules;

import com.maukaim.bulo.common.utils.IoTypeComparator;
import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionService;
import com.maukaim.bulo.definitions.registry.core.StageDefinitionServiceImpl;
import com.maukaim.bulo.definitions.registry.core.TechnicalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.core.validators.FsStagesFSDValidator;
import com.maukaim.bulo.definitions.registry.core.validators.InputsFSDValidator;
import com.maukaim.bulo.definitions.registry.core.validators.InputsTSDValidator;
import com.maukaim.bulo.definitions.registry.core.validators.OutputsFSDValidator;
import com.maukaim.bulo.definitions.registry.core.validators.OutputsTSDValidator;
import com.maukaim.bulo.definitions.registry.core.validators.ParameterDefinitionTSDValidator;
import com.maukaim.bulo.definitions.registry.core.validators.ParameterDefinitionsFSDValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DefinitionModuleBeansConfig {
    @Bean
    public StageDefinitionService stageDefinitionService(StageDefinitionStore definitionStore,
                                                         List<TechnicalStageDefinitionValidator> validators,
                                                         List<FunctionalStageDefinitionValidator> functionalValidators
    ) {
        return new StageDefinitionServiceImpl(definitionStore, validators, functionalValidators);
    }

    @Bean
    public List<TechnicalStageDefinitionValidator> technicalValidators() {
        return List.of(
                new InputsTSDValidator(),
                new ParameterDefinitionTSDValidator(),
                new OutputsTSDValidator()
        );
    }

    @Bean
    public List<FunctionalStageDefinitionValidator> functionalValidators(StageDefinitionStore stageDefinitionStore,
                                                                         StageStore stageStore,
                                                                         IoTypeComparator ioTypeComparator,
                                                                         AcyclicValidator<ContextStageId> acyclicValidator) {
        return List.of(
                new InputsFSDValidator(stageDefinitionStore, stageStore, ioTypeComparator),
                new ParameterDefinitionsFSDValidator(),
                new OutputsFSDValidator(stageStore, stageDefinitionStore, ioTypeComparator),
                new FsStagesFSDValidator(stageStore, acyclicValidator)
        );
    }
}
