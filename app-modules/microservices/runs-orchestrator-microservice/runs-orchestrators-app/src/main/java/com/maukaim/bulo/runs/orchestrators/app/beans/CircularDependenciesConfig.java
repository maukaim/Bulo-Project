package com.maukaim.bulo.runs.orchestrators.app.beans;

import com.maukaim.bulo.runs.orchestrators.app.connectors.StageRunConnectorImpl;
import com.maukaim.bulo.runs.orchestrators.ms.data.lifecycle.StageRunEventConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


//TODO: To get rid of that, use event-based framework to construct core module.
@Configuration
public class CircularDependenciesConfig {
    @Configuration
    public static class FunctionalStageRunCircularDependencyResolver {

        private final StageRunEventConsumer consumer;
        private final StageRunConnectorImpl connector;

        @Autowired
        public FunctionalStageRunCircularDependencyResolver(StageRunEventConsumer consumer, StageRunConnectorImpl connector) {
            this.consumer = consumer;
            this.connector = connector;
        }

        @PostConstruct
        public void injectStageRunEventConsumerInStageRunConnector() {
            connector.setConsumer(consumer);
        }
    }
}
