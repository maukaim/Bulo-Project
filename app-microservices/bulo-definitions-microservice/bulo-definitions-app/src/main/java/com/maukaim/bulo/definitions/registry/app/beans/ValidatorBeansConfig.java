package com.maukaim.bulo.definitions.registry.app.beans;

import com.maukaim.bulo.definitions.registry.core.validators.InputsTSDValidator;
import com.maukaim.bulo.definitions.registry.core.validators.OutputsTSDValidator;
import com.maukaim.bulo.definitions.registry.core.validators.ParametersTSDValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorBeansConfig {

    @Bean
    public ParametersTSDValidator parametersTSDValidator(){
        return new ParametersTSDValidator();
    }

    @Bean
    public InputsTSDValidator inputsTSDValidator(){
        return new InputsTSDValidator();
    }

    @Bean
    public OutputsTSDValidator outputsTSDValidator(){
        return new OutputsTSDValidator();
    }
}
