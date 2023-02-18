package com.maukaim.bulo.stages.app.web;

import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IDefinitionUpdateServiceEndpoint;
import com.maukaim.bulo.ms.shared.system.endpoints.controllers.IStageUpdateServiceEndpoint;
import com.maukaim.bulo.io.stages.system.StageUpdateEventConsumer;
import com.maukaim.bulo.io.stages.system.TechnicalStageDefinitionEventConsumer;
import com.maukaim.bulo.io.stages.system.events.StageUpdateEvent;
import com.maukaim.bulo.io.stages.system.events.TechnicalStageDefinitionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemEndpointsController {

    @RestController
    class ServiceStageUpdateEndpoint implements IStageUpdateServiceEndpoint<StageUpdateEvent> {
        private final StageUpdateEventConsumer consumer;

        @Autowired
        ServiceStageUpdateEndpoint(StageUpdateEventConsumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public void consume(@RequestBody StageUpdateEvent stageUpdateEvent) {
            this.consumer.consume(stageUpdateEvent);
        }
    }

    @RestController
    public class ServiceDefinitionUpdateEndpoint implements IDefinitionUpdateServiceEndpoint<TechnicalStageDefinitionEvent> {
        private TechnicalStageDefinitionEventConsumer definitionEventConsumer;

        public ServiceDefinitionUpdateEndpoint(TechnicalStageDefinitionEventConsumer definitionEventConsumer) {
            this.definitionEventConsumer = definitionEventConsumer;
        }

        @Override
        public void consume(TechnicalStageDefinitionEvent event) {
            this.definitionEventConsumer.consume(event);
        }
    }
}
