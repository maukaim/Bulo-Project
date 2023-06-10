package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.common.utils.IoTypeComparator;
import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.ContextStageId;
import com.maukaim.bulo.definitions.data.StageDefinitionStore;
import com.maukaim.bulo.definitions.data.StageStore;
import com.maukaim.bulo.definitions.registry.core.FunctionalStageDefinitionValidator;
import com.maukaim.bulo.definitions.registry.core.validators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ValidatorBeansConfig {

    @Bean
    public ParameterDefinitionTSDValidator parametersTSDValidator(){
        return new ParameterDefinitionTSDValidator();
    }

    @Bean
    public InputsTSDValidator inputsTSDValidator(){
        return new InputsTSDValidator();
    }

    @Bean
    public OutputsTSDValidator outputsTSDValidator(){
        return new OutputsTSDValidator();
    }

    @Bean
    public List<FunctionalStageDefinitionValidator> functionalStageValidators(StageDefinitionStore stageDefinitionStore,
                                                                              StageStore stageStore,
                                                                              IoTypeComparator ioTypeComparator,
                                                                              AcyclicValidator<ContextStageId> acyclicValidator){
        return List.of(
                new ParameterDefinitionsFSDValidator(),
                new InputsFSDValidator(stageDefinitionStore,stageStore, ioTypeComparator),
                new OutputsFSDValidator(stageStore, stageDefinitionStore, ioTypeComparator),
                new FsStagesFSDValidator(stageStore, acyclicValidator)
        );
    }

    @Bean
    public IoTypeComparator ioTypeComparator(){
        return new IoTypeComparator();
    }

    @Bean
    public AcyclicValidator<ContextStageId> acyclicValidator(){
        return new AcyclicValidator<>();
    }
}
