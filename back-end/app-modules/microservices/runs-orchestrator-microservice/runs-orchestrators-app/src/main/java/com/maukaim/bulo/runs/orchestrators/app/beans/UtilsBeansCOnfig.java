package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.core.factories.FlowRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.FunctionalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.factories.TechnicalStageRunFactory;
import com.maukaim.bulo.runs.orchestrators.core.utils.FlowUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsBeansCOnfig {
    @Bean
    public FlowUtils flowUtils() {
        return new FlowUtils();
    }

    @Bean
    public FlowRunFactory flowRunFactory(){
        return new FlowRunFactory();
    }

    @Bean
    public FunctionalStageRunFactory functionalStageRunFactory(){
        return new FunctionalStageRunFactory();
    }

    @Bean
    public TechnicalStageRunFactory technicalStageRunFactory(){
        return new TechnicalStageRunFactory();
    }

}
