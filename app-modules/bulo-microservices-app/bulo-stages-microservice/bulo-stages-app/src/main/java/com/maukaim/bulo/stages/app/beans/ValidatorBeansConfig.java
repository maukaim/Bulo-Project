package com.maukaim.bulo.stages.app.beans;

import com.maukaim.bulo.stages.core.StageValidator;
import com.maukaim.bulo.stages.core.validators.StageValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorBeansConfig {
    @Bean
    public StageValidator technicalStageValidator() {
        return new StageValidatorImpl();
    }
}
