package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.stages.core.TechnicalStageValidator;
import com.maukaim.bulo.stages.core.validators.TechnicalStageValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorBeansConfig {
    @Bean
    public TechnicalStageValidator technicalStageValidator() {
        return new TechnicalStageValidatorImpl();
    }
}
