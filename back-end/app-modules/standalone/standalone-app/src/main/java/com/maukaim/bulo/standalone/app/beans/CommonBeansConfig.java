package com.maukaim.bulo.standalone.app.beans;

import com.maukaim.bulo.common.utils.ParameterTypeComparator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeansConfig {
    @Bean
    public ParameterTypeComparator parameterTypeComparator() {
        return new ParameterTypeComparator();
    }
}
