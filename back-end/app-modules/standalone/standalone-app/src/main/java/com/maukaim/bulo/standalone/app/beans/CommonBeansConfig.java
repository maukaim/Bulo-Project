package com.maukaim.bulo.standalone.app.beans;

import com.maukaim.bulo.common.utils.IoTypeComparator;
import com.maukaim.bulo.common.utils.ParameterTypeComparator;
import com.maukaim.bulo.commons.models.AcyclicValidator;
import com.maukaim.bulo.commons.models.ContextStageId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeansConfig {
    @Bean
    public ParameterTypeComparator parameterTypeComparator() {
        return new ParameterTypeComparator();
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
